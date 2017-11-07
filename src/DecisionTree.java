import java.util.ArrayList;

public class DecisionTree implements Tree
{
	private ArrayList<Tree> children;
	private ArrayList<Float> thresholds;
	
	
	public DecisionTree(ArrayList<Tree> newChildren, ArrayList<Float> newThresholds)
	{
		this.children = newChildren;
		this.thresholds = newThresholds;
	}
	
	public Tree decideNext(float input)
	{
		//No Decisions
		if(children.size() == 1)
		{
			System.out.println("Decision Tree only one child");
			return children.get(0);
		}
		for(int i = 0; i < thresholds.size();i++)
		{
			if(i == 0)
			{
				if(input <= thresholds.get(0));
					return children.get(0);
			}
			if(thresholds.size() - 1 != i)
			{
				if(input > thresholds.get(i) && input <= thresholds.get(i+1))
					return children.get(i+1);
			}
			else
			{
				if(input > thresholds.get(i))
					return children.get(i+1);
			}
		}
		return null;
	}
}
