
public class RootTree implements Tree 
{
	private String owl;
	
	public RootTree(String newOwl)
	{
		this.owl = newOwl;
	}
	
	String getOwl()
	{
		return owl;
	}
}
