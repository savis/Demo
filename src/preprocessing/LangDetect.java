package preprocessing;
import java.util.ArrayList;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.Language;

public class LangDetect {
	
	public LangDetect(String profileDirectory) throws Exception {
        DetectorFactory.loadProfile(profileDirectory);
	}
	
	 public String detect(String text) throws Exception {
	        Detector detector = DetectorFactory.create();
	        detector.append(text);
	        return( detector.detect());
	       // System.out.println (detector.detect());
	        
	    }
	    public ArrayList<Language> detectLangs(String text) throws Exception {
	        Detector detector = DetectorFactory.create();
	        detector.append(text);
	        return detector.getProbabilities();
	    }


}
