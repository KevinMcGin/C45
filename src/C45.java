import java.util.ArrayList;


public final class C45 
{
	public static Tree C45Algorithm(DataSet data)
	{
		//Find attribute with highest information gain
		int attributeCount = data.getDataEntries().get(0).getValues().size();
		ArrayList<Float> infoGains = new ArrayList<Float>();
		for(int i = 0;i < attributeCount;i++)
		{
			infoGains.add(InfoGain.infoGain(data, i));
		}
		int bestAttributeIndex = 0;
		for(int i = 0;i < attributeCount;i++)
		{
			if(infoGains.get(i) > infoGains.get(bestAttributeIndex))
			{
				bestAttributeIndex = i;
			}
		}
		//Create Root Tree
		//Add children to Root calling C45Algorithm with entries with best attribute value thresholded and all attributes - best
		return null;
	}
}
