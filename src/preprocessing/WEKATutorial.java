package preprocessing;


import java.io.BufferedReader;
import java.io.FileReader;

import weka.classifiers.Classifier; 
import weka.classifiers.Evaluation; 
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.RBFNetwork; 
import weka.core.Attribute; 
import weka.core.FastVector; 
import weka.core.Instance; 
import weka.core.Instances;
import weka.classifiers.trees.J48;

public class WEKATutorial {
	String data=null;
 int count=0;
 FastVector allAttributes = new FastVector(); 
 String[] a_names=new String[3000];
 
 



public void executeWekaTutorial() throws Exception {
	
//used to create the attributes.
FastVector allAttributes = createAttributes(); 

//create a learning data set
 Instances learningDataset= createLearningDataSet(allAttributes);

//form a predictive model
Classifier predictiveModel = learnPredictiveModel( learningDataset); 

//evaluate the model
Evaluation evaluation = evaluatePredictiveModel(predictiveModel,learningDataset); 
System.out.println(evaluation.toSummaryString());

//predict the unknown cases.
predictUnknownCases(learningDataset,predictiveModel);
}


//creates the attributes

private FastVector createAttributes()throws Exception {

// declare a fast vector to store all attributes

//read the keywords
BufferedReader tsvFile = new BufferedReader(new FileReader("/Users/administrator/Desktop/attribute.tsv"));
data= tsvFile.readLine();

while(data!= null)
{	   
count = count +1; 
	   
//nominal attributes create a fast vector and mention the values it can take.
FastVector genderAttributeValues = new FastVector(2); 
genderAttributeValues.addElement("0"); 
genderAttributeValues.addElement("1"); 

//add the nominal attributes to fast vector
allAttributes.addElement(new Attribute(data,genderAttributeValues)); 	   
data= tsvFile.readLine();
}

//adding the categories
FastVector categories = new FastVector(3);
categories.addElement("Politics");
categories.addElement("Issues");
categories.addElement("Technology");
categories.addElement("null");

allAttributes.addElement(new Attribute("categories",categories));
return allAttributes;
}



//creates the training dataset
private Instances createLearningDataSet(FastVector allAttributes) throws Exception{ 
	int ind=0;
	Object[] name= allAttributes.toArray();
	String String_Array[]=new String[name.length];
	String[]s;
	

	//getting the attribute names from fast vector
	for (int i=0;i<name.length;i++) 
	{
		String_Array[i]=name[i].toString();
	}
	
	//copying the attribute names to a  string array storing in a_names;
	for(String r_name:String_Array)
	{ 
		
	s=r_name.split(" ");
	//System.out.println(s[1]);
	if(s[1]!=null)
	{
	a_names[ind]=s[1];
	ind=ind+1;
	
	}
	}
	
	Instances trainingDataSet =new Instances("wekaTutorial", allAttributes, 25); 
	
	//specifies the attribute to be predicted.
	trainingDataSet.setClassIndex(count); 
	System.out.println(count);
	//creating instances
	BufferedReader tsv = new BufferedReader(new FileReader("/Users/administrator/Desktop/samples/predict.tsv"));
	String data=tsv.readLine();
	while(data!= null)
		   
		   //adding a single instance
	{     
		Instance instance = new Instance(count+1); 
	
	System.out.println("created an instance");
	       //setting it to training dataset
	       instance.setDataset(trainingDataSet);
	       
	       //checking is the tokens are present as attributes
		   String[] data1 = data.split("[{}]");
		  
		   for (String item:data1) 
			   
		   { 
			   if(item.equals(""))
			   {
			   }
			   else
			   {
			  // System.out.println("The item is "+item);
			   
			   int j=0;
			 for(String search:a_names)
			 {    
				 
				 //System.out.println(search);
				 
				 if(search.equals("categories"))
				 {  
					 String[] category=data.split("\t");
					// System.out.println("category is "+category[1]);
					 instance.setValue(j,category[1]);
					 break;
					 
				 }
				 else if(item.equals(search))
				 {   
					// System.out.println("they are equal");
					 instance.setValue( j,"1");
				 }
				 
				 else
				 {   
					 
					 instance.setValue( j,"0"); 
				 }
				 
				 j=j+1;
				 
			 }
			 
			   }
		   }
		   
		  
		   System.out.println();
		   trainingDataSet.add(instance);
		   
		   data=tsv.readLine();
	}
	
	return trainingDataSet;
}







private Classifier learnPredictiveModel(Instances learningDataset) throws Exception {
	Classifier classifier = getClassifier(); 
	classifier.buildClassifier(learningDataset); 
	return classifier;
}

private Classifier getClassifier() { 
	
//RBFNetwork rbfLearner = new RBFNetwork();
	// NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
	J48 tree = new J48();  
//nb.setNumClusters(3); 
return tree;
}

private Evaluation evaluatePredictiveModel(Classifier classifier, Instances learningDataset) throws Exception {
	Evaluation learningSetEvaluation = new Evaluation(learningDataset);
	learningSetEvaluation.evaluateModel(classifier,learningDataset);
	return learningSetEvaluation;
}

private void predictUnknownCases(Instances learningDataset, Classifier predictiveModel)
throws Exception {
	
	BufferedReader tsvFile = new BufferedReader(new FileReader("/Users/administrator/Desktop/samples/predict.tsv"));
	String data=tsvFile.readLine();
	while(data!= null)
		
		   //adding a single instance
	{      Instance instance = new Instance(count+1); 
	       //setting it to training dataset
	      instance.setDataset(learningDataset);
	       
	       //checking if the tokens are present as attributes
		   String[] dataArray = data.split("[{}]");
		   for (String item:dataArray) 
		   { 
			   int j=0;
			 for(String search:a_names)
			 {   
				 
				 
				 if(search.equals("categories"))
				 {  
					
					 instance.setValue(j,"null");
					 break;
					 
				 }
				 else if(item==search)
				 {   
					 instance.setValue( j,1);
				 }
				 else
				 {
					 instance.setValue(j, 0);
				 }
				 j=j+1;
			 }
			 
		   }
		   double ans =predictiveModel.classifyInstance(instance); 
		   //System.out.println("Predicted number of logins [age=32]: "); 
			//System.out.println("ans = " +ans ); 
			//System.out.println(", actual: " + learningDataset.classAttribute().value((int) learningDataset.instance().classValue()));
			//System.out.println(", predicted: " + learningDataset.classAttribute().value(((int)ans)));
		   data=tsvFile.readLine();
		   
	}
	
}
}