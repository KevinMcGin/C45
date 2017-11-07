import java.util.ArrayList;

public class DataSet 
{
	private ArrayList<Attributes> dataEntries;
	
	public DataSet(ArrayList<Attributes> newDataEntries)
	{
		this.dataEntries = newDataEntries;
	}
	
	public ArrayList<Attributes> getDataEntries()
	{
		return dataEntries;
	}
}
