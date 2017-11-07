import java.util.ArrayList;

public class Attributes 
{
	private ArrayList<Float> values;
	private String classified;
	
	public Attributes(ArrayList<Float> newValues, String newClassified)
	{
		this.values = newValues;
		this.classified = newClassified;
	}
	
	public ArrayList<Float> getValues()
	{
		return values;
	}
	public String getClassified()
	{
		return classified;
	}
}
