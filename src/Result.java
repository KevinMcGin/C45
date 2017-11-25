import java.util.ArrayList;

public class Result 
{
	int correct;
	int incorrect;
	ArrayList<ArrayList<Integer>> confusionMatrix;//Actual x Classified
	ArrayList<String> titles;
	
	Result(ArrayList<String> newTitles)
	{
		correct = 0;
		incorrect = 0;
		titles = newTitles;
		confusionMatrix = new ArrayList<ArrayList<Integer>>();
	}
	void addResult(String classified, String actual)
	{
		if(classified.equals(actual))
		{
			correct++;
		}
		else
		{
			incorrect++;
		}
		
		for(int i = 0; i < titles.size();i++)
		{
			if(actual.equals(titles.get(i)))
			{
				for(int j = 0; j < titles.size();j++)
				{
					if(classified.equals(titles.get(j)))
					{
						confusionMatrix.get(i).set(j, confusionMatrix.get(i).get(j)+1);
					}
				}
			}
		}
	}
	public String toString()
	{
		String string = "Accurary: " + ((float)(correct))/(correct+incorrect) + "\n\n";
		string += "Actual x Classified\n";
		for(int i = 0; i < titles.size();i++)
		{
			string += titles.get(i) + " ";
		}
		string += "\n";
		for(int i = 0; i < confusionMatrix.size();i++)
		{
			for(int j = 0; j < confusionMatrix.get(i).size();j++)
			{
				string += confusionMatrix.get(i).get(j) + " ";
			}
			string += "\n";
		}
		return string + "\n";
	}
}
