import java.util.ArrayList;


public final class C45 
{
	public static Tree C45Algorithm(DataSet data)
	{
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
		//Create Root Tree
		DecisionTree root = new DecisionTree(bestAttributeIndex);
		//Add children to Root calling C45Algorithm with entries with best attribute value thresholded and all attributes - best
		//Create key of attributes with each value of attribute best
		//
		return null;
	}
}
