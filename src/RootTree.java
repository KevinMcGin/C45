
public class RootTree implements Tree 
{
	private String classify;
	
	public RootTree(String newClassify)
	{
		this.classify = newClassify;
	}
	public String classify(Attributes input)
	{
		return classify();
	}
	
	String classify()
	{
		return classify;
	}
	
	@Override
	public String toString()
	{
		return classify;
	}
}
