package preprocessing;


public class tweet_Main {
	
	 public static void main(String[] arg) throws Exception {	 
		 
			tweetSep p = new tweetSep();
		    fileRW r = new fileRW();
			r.RW();
			p.AnalyzeTweet();
		    WEKATutorial w = new WEKATutorial();
		    w.executeWekaTutorial();
		 
			 
		 }
	 
	}


