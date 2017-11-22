
public class RootTree implements Tree 
{
	private String classify;
	
	public RootTree(String newClassify)
	{
		this.classify = newClassify;
	}
	
	String classify()
	{
		return classify;
	}
}
