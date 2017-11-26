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
		for(int i = 0; i < titles.size();i++)
		{
			confusionMatrix.add(new ArrayList<Integer>());
			for(int j = 0; j < titles.size();j++)
			{
				confusionMatrix.get(i).add(0);
			}
		}
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
						break;
					}
				}
				break;
			}
		}
	}
	public String toString()
	{
		String string = "Accurary: " + ((float)(correct))/(correct+incorrect) + "\r\n\r\n";
		string += "Actual * Classified\r\n";
		for(int i = 0; i < titles.size();i++)
		{
			string += titles.get(i) + " ";
		}
		for(int i = 0; i < confusionMatrix.size();i++)
		{
			string += "\r\n";
			for(int j = 0; j < confusionMatrix.get(i).size();j++)
			{
				string += confusionMatrix.get(i).get(j) + " ";
			}
		}
		return string;
	}
}
