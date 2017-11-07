import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main 
{
	public float entropy(DataSet data)
	{
		Map<String,Integer> classCount = new HashMap<String,Integer>();
		int classSize = data.getDataEntries().size();
		float entropy = 0;
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
			entropy -= (p)*Math.log(p)/Math.log(2);
		}		
		return entropy;
	}
	public float infoGain(DataSet data, int indexAttribute)
	{
		float gain = entropy(data);
		float inverseClassSize = 1f / data.getDataEntries().size();
		
		Map<Float,Integer> valueCount = new HashMap<Float,Integer>();
		for(Attributes thisAttribute : data.getDataEntries())
		{
			if(valueCount.containsValue(thisAttribute.getClassified()))
			{
				valueCount.put(thisAttribute.getValues().get(indexAttribute), valueCount.get(thisAttribute.getValues().get(indexAttribute))+1);
			}
			else
			{
				valueCount.put(thisAttribute.getValues().get(indexAttribute),1);
			}
		}
		//TODO: finsh gain calculation
		
		
		return gain;
	}	
	public static void main(String[] args) 
	{
		
	}
}
