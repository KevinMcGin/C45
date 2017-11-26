import java.util.ArrayList;

public class DataSet 
{
	private ArrayList<Attributes> dataEntries;
	
	public DataSet()
	{
		dataEntries = new ArrayList<Attributes>();
	}
	public DataSet(DataSet data)
	{
		dataEntries = new ArrayList<Attributes>(data.getDataEntries());
	}
	
	public DataSet(ArrayList<Attributes> newDataEntries)
	{
		this.dataEntries = newDataEntries;
	}
	
	public void addEntry(Attributes attribute)
	{
		dataEntries.add(attribute);
	}
	
	public void removeEntry(int index)
	{
		dataEntries.remove(index);
	}
	
	public ArrayList<Attributes> getDataEntries()
	{
		return dataEntries;
	}
	
	public void splitDataRandomly(DataSet train,DataSet test)
	{		
		ArrayList<Integer> trainIndices = new ArrayList<Integer>();
		ArrayList<Integer> testIndices = new ArrayList<Integer>();
		
		for(int i = 0; i < dataEntries.size();i++)
		{
			trainIndices.add(i);
		}
		int oneThird = (int)(trainIndices.size()*(1f/3f));
		for(int i = 0; i < oneThird;i++)
		{
			int randIndex = (int)(Math.random()*trainIndices.size());
			testIndices.add(trainIndices.get(randIndex));
			trainIndices.remove(randIndex);
		}
		for(Integer index : trainIndices)
		{
			train.addEntry(new Attributes(dataEntries.get(index)));
		}
		for(Integer index : testIndices)
		{
			test.addEntry(new Attributes(dataEntries.get(index)));
		}
	}

	//Inclusive start, exclusive end
	public DataSet splitDataRemoveAttribute(int start, int end,int attributeRemove)
	{
		DataSet data = new DataSet();
		for(int i=start;i < end;i++)
		{
			data.addEntry(new Attributes(dataEntries.get(i)));
		}
		for(Attributes attributes : data.getDataEntries())
		{
			attributes.getValues().remove(attributeRemove);
		}
		return data;
	}

	public DataSet splitData(int start, int end)
	{
		DataSet data = new DataSet();
		for(int i=start;i < end;i++)
		{
			data.addEntry(dataEntries.get(i));
		}
		return data;
	}
}