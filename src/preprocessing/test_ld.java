/*package preprocessing;


	import java.io.*;
	import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
	import java.io.IOException;
	import java.io.StringReader;
import java.util.ArrayList;
	
	
	public class test_ld {
		
		public static int sno = 0;
		 public static FileWriter f ;
			public static BufferedWriter writer1; 
			 
			public static void main(String[] arg) throws Exception {
				 
				 // finding out the language and categorizing based on them
				 
				  
				 
				 // stemmina nd removing stop words from tweets using standard analyzer in lucene.
				 f= new FileWriter("/Users/administrator/Desktop/samples/E1.tsv");
				 writer1= new BufferedWriter(f);
				 		
				 String t_tweet =null;
				 String dataRow=null;
				 BufferedReader tsvFile = new BufferedReader(new FileReader("/Users/administrator/Desktop/samples/E1_token.tsv"));
				 
				  
				  //read a tweet
				   dataRow = tsvFile.readLine();

				   while (dataRow != null)
				   {
				    String[] dataArray = dataRow.split("\t");
				    sno=sno+1;
				    
				    //read the tweet
					 t_tweet = dataArray[1];
					 
					 // find the language
					 LangDetect l = new LangDetect("/Users/administrator/refDM/langdetect-09-13-2011/profiles");
					 String lang=l.detect(t_tweet);
					 writer1.append(lang);
					 writer1.append("\n");
					 //analyze(t_tweet, lang);
					 dataRow = tsvFile.readLine();
					 
					 tsvFile.close();
					   writer1.close();

				   }	
		
}*/
			 
			/*   public static void main(String args[]) throws Exception
				{
				LangDetect l = new LangDetect("/Users/administrator/refDM/langdetect-09-13-2011/profiles");
				
				System.out.println(l.detect("RT @sokkari: عمار الشريعي: المذيع كان بيذيع: لا خيول ولا جمال.... امال دول كانوا ايه؟؟ فيلة؟؟ #Egypt #jan2"));
				}
	}*/
