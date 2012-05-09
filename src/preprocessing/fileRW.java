package preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class fileRW {
	
	 int count =0;
	 String tweet=null;;
     String dataRow = null;
     
     public void RW()throws Exception
     {

  BufferedReader tsvFile = new BufferedReader(new FileReader("/Users/administrator/Desktop/samples/egypt.tsv"));
  BufferedWriter writer1 = new BufferedWriter(new FileWriter("/Users/administrator/Desktop/samples/E1.tsv"));



   dataRow = tsvFile.readLine(); // Read first line.
  // The while checks to see if the data is null. If 
  // it is, we've hit the end of the file. If not, 
  // process the data.

  while (dataRow != null)
  {
   String[] dataArray = dataRow.split("\t");
   for (String item:dataArray) 
   { 
	 count = count +1; 
   } 
   
   // checking for tabs in tweet messages
	 if(count <=10)
	 {  
		
		 tweet = dataArray[1];
		
	 }
	 count=0;
	 
	 //writing the tweet into the file 
	 writer1.append( tweet+"\n");
	 
       
   dataRow = tsvFile.readLine(); // Read next line of data.
  }
  // Close the file once all data has been read.
  tsvFile.close();
  writer1.close();

  // End the printout with a blank line.
  System.out.println();
}
}





