package wordTree.driver;

import java.util.ArrayList;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {

	ArrayList<String> resultsArray;
	MyLogger logger;

	public Results(MyLogger loggerIn) {
		resultsArray = new ArrayList<String>(5);
		logger = loggerIn;
		logger.writeMessage("Results Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
	}
	
	/*
		store new results into the results array
		@param String result
		@ret none
	*/
	public void storeNewResult(String result) {
		logger.writeMessage("Writing to results object", MyLogger.DebugLevel.IN_RESULTS);
		resultsArray.add(result);
	}
	
	public void writeToScreen(BinarySearchTree t) {
		t.inorder();
	}
	
	public void writeSchedulesToFile(FileDisplay fd, BinarySearchTree t) {
		fd.writeToFile("The total number of words: " + t.totalWords);
		fd.writeToFile("The total number of characters: " + t.totalCharacters);
		fd.writeToFile("The total number of distinct words: " + t.totalDistinct);
	}
	
	/*
		getter method for the results array
		@param none
		@ret ArrayList<String> resultsArray
	*/
	public ArrayList<String> getResultsArray() {
		return resultsArray;
	}
	
	/*
		toString for debugging objects with print statements
		@param none
		@return String description of the class
	*/
	public String toString() {
		return "Results class";
	}

}
