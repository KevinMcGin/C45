
public class RootTree implements Tree 
{
	private String classify;
	
	public RootTree(String newClassify)
	{
		this.classify = newClassify;
	}
	
	String getClassify()
	{
		return classify;
	}
}
