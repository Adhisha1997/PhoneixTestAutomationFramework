import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class DemoCSVReader {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub

		File credtFile = new File(System.getProperty("user.dir")+"/src/main/resources/TestData/loginCredententials.csv");
		FileReader filereader = new FileReader(credtFile);
		CSVReader csvread = new CSVReader(filereader);
		
		List<String[]> dataList=csvread.readAll();
		
		for(String[] dataArray :dataList) {
			for(String data: dataArray) {
				System.out.print(data +" ");
				
			}
			System.out.println();
		}
		
	}

}
