package wordTree.driver;

import java.util.StringTokenizer;

public class PopulateThread extends Thread {

	FileProcessor fileProc;
	Results res;
	MyLogger log;
	BinarySearchTree tree;

	public PopulateThread() { }

	public PopulateThread(FileProcessor fp, Results r, MyLogger l, BinarySearchTree t) {
		log = l;
		log.writeMessage("PopulateThread Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
		fileProc = fp;
		res = r;
		tree = t;
	}
	
	/*
		Method that does the insertion into tree
		@param none
		@ret none
	*/
	public synchronized void run() {
		log.writeMessage("PopulateThread run() called", MyLogger.DebugLevel.IN_RUN);
	
		String line;
		
		while( ( line = fileProc.readLine() ) != null) {
			StringTokenizer multiTokenizer = new StringTokenizer(line, " ");
			
			while(multiTokenizer.hasMoreTokens()) {
				String split = multiTokenizer.nextToken();
				tree.insert(split);
			}
		}
	}

}
