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
	
	public Tree decideNext(Attributes input)
	{
		if(children.size() == 0)
		{
			System.out.println("Decision Tree no child");
			return null;
		}
		if(children.size() == 1)
		{
			System.out.println("Decision Tree only one child");
			return children.get(0);
		}
		float attributeValue = input.getValues().get(indexAtrribute);
		for(int i = 0; i < thresholds.size();i++)
		{
			if(i == 0)
			{
				if(attributeValue <= thresholds.get(0))
					return children.get(0);
			}
			if(thresholds.size() - 1 != i)
			{
				if(attributeValue > thresholds.get(i) && attributeValue <= thresholds.get(i+1))
					return children.get(i+1);
			}
			else
			{
				if(attributeValue > thresholds.get(i))
					return children.get(i+1);
			}
		}
		return null;
	}
}
