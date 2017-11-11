import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        
		//Split dataset 1/3, 2/3 randomly
		DataSet train = new DataSet();
		DataSet test = new DataSet();
		dataSet.splitData(train, test);
		//Run C45 algorithm on 2/3 dataset
		//Run test on 1/3 dataset and output to txt file
		//Repeat 10 times
	}
}
