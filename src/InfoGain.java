import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public final class InfoGain 
{
	private static float entropy(DataSet data)
	{
		Map<String,Integer> classCount = new HashMap<String,Integer>();
		int classSize = data.getDataEntries().size();
		float entropyValue = 0;
		for(Attributes thisAttribute : data.getDataEntries())
		{
			if(classCount.containsValue(thisAttribute.getClassified()))
			{
				classCount.put(thisAttribute.getClassified(), classCount.get(thisAttribute.getClassified())+1);
			}
			else
			{
				classCount.put(thisAttribute.getClassified(),1);
			}
		}
		for (float value : classCount.values()) 
		{
			float p = value/classSize;
			entropyValue -= (p)*Math.log(p)/Math.log(2);
		}		
		return entropyValue;
	}
	
	public static float infoGain(DataSet data, int indexAttribute)
	{
		float gain = entropy(data);
		float inverseClassSize = 1f / data.getDataEntries().size();
		float tempSubSetEntropySum = 0;
		
		Map<Float,Integer> valueCount = new HashMap<Float,Integer>();
		Map<Float,DataSet> subSets = new HashMap<Float,DataSet>();
		for(Attributes thisAttribute : data.getDataEntries())
		{
			if(valueCount.containsValue(thisAttribute.getValues().get(indexAttribute)))
			{
				valueCount.put(thisAttribute.getValues().get(indexAttribute), 
						valueCount.get(thisAttribute.getValues().get(indexAttribute))+1);
				
				ArrayList<Attributes> attributeArray = new ArrayList<Attributes>();
				attributeArray.add(thisAttribute);
				subSets.put(thisAttribute.getValues().get(indexAttribute), 
						new DataSet(attributeArray));
			}
			else
			{
				valueCount.put(thisAttribute.getValues().get(indexAttribute),1);
				
				subSets.get(thisAttribute.getValues().get(indexAttribute)).addEntry(thisAttribute);
			}
		}
		
		for (Map.Entry<Float,DataSet> entry : subSets.entrySet())
		{
			tempSubSetEntropySum += valueCount.get(entry.getKey()) * entropy(entry.getValue());
		}
		tempSubSetEntropySum *= inverseClassSize;
		gain -= tempSubSetEntropySum;
		
		return gain;
	}	
}
