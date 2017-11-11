import java.util.ArrayList;

public class DecisionTree implements Tree
{
	private ArrayList<Tree> children;
	private ArrayList<Float> thresholds;
	private int indexAtrribute;
	
	
	public DecisionTree(ArrayList<Tree> newChildren, ArrayList<Float> newThresholds,int newIndexAtrribute)
	{
		this.children = newChildren;
		this.thresholds = newThresholds;
		this.indexAtrribute = newIndexAtrribute;
	}
	
	public String classify(Attributes input)
	{
		if(children.size() == 0)
		{
			System.out.println("Decision Tree no child");
			return null;
		}
		if(children.size() == 1)
		{
			System.out.println("Decision Tree only one child");
			return ((RootTree)children.get(0)).getClassify();
		}
		float attributeValue = input.getValues().get(indexAtrribute);
		Tree nextTree = null;
		for(int i = 0; i < thresholds.size();i++)
		{
			if(i == 0)
			{
				if(attributeValue <= thresholds.get(0))
				{
					nextTree = children.get(0);
					break;
				}
			}
			if(thresholds.size() - 1 != i)
			{
				if(attributeValue > thresholds.get(i) && attributeValue <= thresholds.get(i+1))
				{
					nextTree = children.get(i+1);
					break; 
				}
			}
			else
			{
				if(attributeValue > thresholds.get(i))
				{
					nextTree = children.get(i+1);
					break;
				}
			}
		}
		if(nextTree != null)
			if(!(nextTree instanceof DecisionTree))
				return ((DecisionTree)nextTree).classify(input);
			else
				return ((RootTree)nextTree).getClassify();
		else
			return null;
	}
}
