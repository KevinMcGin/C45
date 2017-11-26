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
	public Attributes(Attributes attributes)
	{
		this.values = new ArrayList<Float>(attributes.getValues());
		this.classified = attributes.getClassified();
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
