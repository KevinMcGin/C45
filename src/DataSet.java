import java.util.ArrayList;

public class DataSet 
{
	private ArrayList<Attributes> dataEntries;
	
	public DataSet()
	{
		dataEntries = new ArrayList<Attributes>();
	}
	
	public DataSet(ArrayList<Attributes> newDataEntries)
	{
		this.dataEntries = newDataEntries;
	}
	
	public void addEntry(Attributes attribute)
	{
		dataEntries.add(attribute);
	}
	
	public ArrayList<Attributes> getDataEntries()
	{
		return dataEntries;
	}
	
	public void splitData(DataSet train,DataSet test)
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
			train.addEntry(dataEntries.get(index));
		}

		for(Integer index : testIndices)
		{
			test.addEntry(dataEntries.get(index));
		}
	}
}