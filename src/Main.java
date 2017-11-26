import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Main 
{	
	public static void main(String[] args) throws IOException 
	{
		//Load attributes and add to a dataset from csv file
		DataSet dataSet = new DataSet();
		
		BufferedReader reader = new BufferedReader(new FileReader(
				"owls15.csv"));

		// read file line by line
		String line = null;
		Scanner scanner = null;

		while ((line = reader.readLine()) != null) {
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			ArrayList<Float> attributes = new ArrayList<Float>();
			while (scanner.hasNext()) {
				String data = scanner.next();
				try
				{
					attributes.add(Float.parseFloat(data));
				}
				catch(NumberFormatException e)
				{
				  dataSet.addEntry(new Attributes(attributes,data));
				}
			}
		}
		//close reader
		reader.close();
		//Init Result Object
		HashSet<String> titles = new HashSet<String>();
		for(Attributes attributes : dataSet.getDataEntries())
		{
			titles.add(attributes.getClassified());
		}
		Result result = new Result(new ArrayList<String>(titles));
		for(int i = 0; i < 10; i++)
		{
			//Split dataset 1/3, 2/3 randomly
			DataSet train = new DataSet();
			DataSet test = new DataSet();
			dataSet.splitDataRandomly(train, test);
			//Run C45 algorithm on 2/3 dataset
			Tree root = C45.C45Algorithm(train);
			//Run test on 1/3 dataset and output to txt file
			for(Attributes attributes : test.getDataEntries())
			{
				String classified =  root.classify(attributes);
				String actual = attributes.getClassified();
				result.addResult(classified, actual);
			}
		}
		//Output results
		System.out.println(result.toString());
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter("results.txt"));
		    writer.write( result.toString());

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        	writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
}
