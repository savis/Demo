package preprocessing;


import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import java.util.ArrayList;
import org.apache.lucene.analysis.fr.*;
import org.apache.lucene.analysis.ar.*;



	public class tweetSep{
    
	//declaring variables	
	public static  Analyzer analyzer1 = new StandardAnalyzer(Version.LUCENE_34);
	public  Analyzer french1= new FrenchAnalyzer(Version.LUCENE_34);
	public static ArabicAnalyzer ar = new ArabicAnalyzer(Version.LUCENE_34); 
	public static FileWriter f ;
    public static BufferedWriter writer1; 
	public static int sno=0;	 
	 
	public void AnalyzeTweet()throws Exception
	{   
		 tweetSep t = new tweetSep();
		String t_tweet1=null; 
		
		//get the classifiers for lang detection
		LangDetect l = new LangDetect("/Users/administrator/refDM/langdetect-09-13-2011/profiles");
		
		//file writer
		 f= new FileWriter("/Users/administrator/Desktop/samples/E1_token.tsv");
		 writer1= new BufferedWriter(f);
		 		
		 String t_tweet =null;
		 String dataRow=null;
		 
		 //Reader
		 BufferedReader tsvFile = new BufferedReader(new FileReader("/Users/administrator/Desktop/samples/E1.tsv"));
		 
		  
		  //read a tweet
		   dataRow = tsvFile.readLine();
		   while (dataRow != null)
		   {
			   sno=sno+1;
		    String[] dataArray = dataRow.split("\t");
		   
			 t_tweet = dataArray[0];
			  t_tweet1= new String(t_tweet.getBytes(),"UTF-8"); 	
			
			 // find the language
			 if(t_tweet!=null)
			 {
				 System.out.println(sno);
			 String lang=l.detect(t_tweet);
			 System.out.println(lang);
			 
			 //form tokens
			 t_tweet1= new String(t_tweet.getBytes(),"UTF-8"); 
		    
		 if(lang.equals("en"))
		 {
		  t.displayTokens(analyzer1.reusableTokenStream("contents", new StringReader(t_tweet1)));
		 }
		 else if(lang.equals("ar")){
		 t.displayTokens(ar.tokenStream("contents", new StringReader(t_tweet1)));
		 }
		 else
		 {
		  t.displayTokens(french1.tokenStream("contents", new StringReader(t_tweet1)));
		 }
			 
			 }
		dataRow = tsvFile.readLine();
		   }   
	
	}
	
	
	 /*public static void main(String[] arg) throws Exception {	 
	 
			tweetSep p = new tweetSep();
			//fileRW r = new fileRW();
			//r.RW();
			p.AnalyzeTweet();
		
		 
			 
		 }*/
	 
	 

		 
	
	public void displayTokens(TokenStream stream) throws IOException {
		 
		 
		
		TermAttribute term = stream.addAttribute(TermAttribute.class); 
		 
		 while(stream.incrementToken()) 
		 { 
			writer1.append( "{"+term.term()+"}");
		 
		 }
		 
		 writer1.append("\n");
		 
	 
	}
		
	}
	 
	 

