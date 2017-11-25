import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class C45 
{	
	public static Tree C45Algorithm(DataSet data)
	{
		//If examples is empty return default class
		if(data.getDataEntries().size() == 0)
		{
			return new RootTree("No Info");
		}
		//If all examples classified same, return that classification
		String firstClass = data.getDataEntries().get(0).getClassified();
		boolean classifiedSame = true;
		for(Attributes attributes : data.getDataEntries())
		{
			if(attributes.getClassified() != firstClass)
			{
				classifiedSame = false;
				break;
			}
		}
		if(classifiedSame)
		{
			return new RootTree(firstClass);
		}

		//If attributes are empty return majority class
		if(data.getDataEntries().get(0).getValues().size() == 0)
		{
			Map<String,Integer> classCount = new HashMap<String,Integer>();
			for(Attributes attributes : data.getDataEntries())
			{
				if(classCount.containsValue(attributes.getClassified()))
				{
					classCount.put(attributes.getClassified(), 
							classCount.get(attributes.getClassified())+1);
				}
				else
				{
					classCount.put(attributes.getClassified(),1);
				}
			}
			String maxClass = firstClass;
			for (Map.Entry<String,Integer> classified : classCount.entrySet())
			{
				if(classified.getValue() > classCount.get(maxClass))
				{
					maxClass = classified.getKey();
				}
			}
			return new RootTree(maxClass);
		}
		
		//Find attribute with highest information gain
		ArrayList<Float> infoGains = new ArrayList<Float>();
		int attributeCount = data.getDataEntries().get(0).getValues().size();
		int bestAttributeIndex = 0;
		for(int i = 0;i < attributeCount;i++)
		{
			infoGains.add(InfoGain.infoGain(data, i));
		}
		for(int i = 0;i < attributeCount;i++)
		{
			if(infoGains.get(i) > infoGains.get(bestAttributeIndex))
			{
				bestAttributeIndex = i;
			}
		}
		//Threshold attributes
		DataSet tempData = new DataSet(data);
		DataSet orderedData = new DataSet();
		int minIndex = 0;
		while(tempData.getDataEntries().size() > 0)
		{
			for(int i = 0; i < tempData.getDataEntries().size();i++)
			{
				if(tempData.getDataEntries().get(i).getValues().get(bestAttributeIndex) < tempData.getDataEntries().get(minIndex).getValues().get(bestAttributeIndex))
				{
					minIndex = i;
				}
			}
			orderedData.addEntry(tempData.getDataEntries().get(minIndex));
			tempData.removeEntry(minIndex);
		}
		ArrayList<Float> thresholds = thresholdAttributes(orderedData,bestAttributeIndex);
		Collections.sort(thresholds);
		
		//Add children to Root calling C45Algorithm with entries with best attribute value thresholded and all attributes - best
		ArrayList<Tree> children = new ArrayList<Tree>();
		int start = 0;
		int end = 0;
		for(int i = 0; i <thresholds.size();i++)
		{
			for(int j = 0; j < orderedData.getDataEntries().size();j++)
			{
				if(orderedData.getDataEntries().get(j).getValues().get(bestAttributeIndex) > thresholds.get(i))
				{
					start = end;
					end = j;
				}
			}
			children.add(C45Algorithm(orderedData.splitDataRemoveAttribute(start, end,bestAttributeIndex)));
			if(i+1 > thresholds.size())
				children.add(C45Algorithm(orderedData.splitDataRemoveAttribute(end, thresholds.size(),bestAttributeIndex)));
		}
		//Create Tree node
		DecisionTree tree = new DecisionTree(children,thresholds,bestAttributeIndex);
		return tree;
	}
	
	private static ArrayList<Float> thresholdAttributes(DataSet data,int attributeIndex)
	{
		float minInfoGain = 0.9f;
		
		DataSet dataOne = new DataSet(data);
		DataSet dataTwo = new DataSet();
		float infoGainAvg = -1;
		Float threshold = null;
		int dataIndexThreshold = 0;
		
		while(dataOne.getDataEntries().size() > 0)
		{			
			float thisInfoGainAvg = (InfoGain.infoGain(dataOne, attributeIndex) + InfoGain.infoGain(dataTwo, attributeIndex)/2);
			if(thisInfoGainAvg > infoGainAvg)
			{
				infoGainAvg = thisInfoGainAvg;
			}
			threshold = (dataOne.getDataEntries().get(dataOne.getDataEntries().get(attributeIndex).getValues().size()-1).getValues().get(attributeIndex) +
					dataTwo.getDataEntries().get(0).getValues().get(attributeIndex))/2;
			
			dataTwo.getDataEntries().add(dataOne.getDataEntries().get(dataOne.getDataEntries().size()));
			dataOne.getDataEntries().remove(dataOne.getDataEntries().get(dataOne.getDataEntries().size()));
		}
		ArrayList<Float> thresholds = new ArrayList<Float>();
		if(threshold != null)
			thresholds.add(threshold);
		else
			return thresholds;
		if(infoGainAvg < minInfoGain)
		{
			thresholds.addAll(thresholdAttributes(data.splitData(0, dataIndexThreshold),attributeIndex));
			thresholds.addAll(thresholdAttributes(data.splitData(dataIndexThreshold,data.getDataEntries().size()),attributeIndex));  
		}
		
		return thresholds;
	}
}
