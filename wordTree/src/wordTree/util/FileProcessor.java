package wordTree.driver;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileProcessor {
	
	private String inputfile;
	private Scanner scan;
	
	public FileProcessor() { }
	
	/*
		FileProcessor Constructor
		@param String fileIn the name of the input.txt file
		@ret none
	*/
	public FileProcessor(String fileIn, MyLogger loggerIn) {
		inputfile = fileIn;
		loggerIn.writeMessage("FileProcessor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
		try {
			File textFileInput = new File(inputfile);
			scan = new Scanner(textFileInput);
		} catch(FileNotFoundException e) {
			if(inputfile.equals("")) System.out.println("Warning: no input file supplied.\n");
			else System.out.println("Warning: " + inputfile + " not found.\n");
			System.exit(0);
		} finally {

		}
		
	}
	
	/*
		Returns one line at a time when reading from a file
		@param none
		@ret String lineRead the line read from input file
	*/
	public synchronized String readLine() {
		String lineRead = "";
		//should the method be synchronized or should the block reading the line be
		//synchronized(this) {
			if(scan.hasNextLine()) {
				lineRead = scan.nextLine();
			} else {
				return null;
			}
		
			return lineRead;
		//}
		
		
	}
	
	/*
		Closes the scanner used for file input
		@param none
		@ret none
	*/
	public void closeInput() {
		scan.close();
	}
	
	/*
		Getter method for inputfile name
		@param none
		@ret String inputfile name
	*/
	public String getInputFile() {
		return inputfile;
	}
	
	/*
		Getter method for Scanner
		@param none
		@ret Scanner variable
	*/
	public Scanner getScanner() {
		return scan;
	}
	
	/*
		toString for debugging objects with print statements
		@param none
		@return String description of the class
	*/
	public String toString() {
		return "FileProcessor class";
	}
	

}
