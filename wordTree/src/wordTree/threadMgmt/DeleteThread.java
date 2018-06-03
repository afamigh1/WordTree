package wordTree.driver;

public class DeleteThread extends Thread {

	FileProcessor fileProc;
	Results res;
	MyLogger log;
	BinarySearchTree tree;
	String deleteWord;

	public DeleteThread() { }

	public DeleteThread(FileProcessor fp, Results r, MyLogger l, BinarySearchTree t, String delWord) {
		log = l;
		log.writeMessage("DeleteThread Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
		fileProc = fp;
		res = r;
		tree = t;
		deleteWord = delWord;
	}
	
	/*
		Method that does the deletion from tree
		@param none
		@ret none
	*/
	public synchronized void run() {
		log.writeMessage("DeleteThread run() called", MyLogger.DebugLevel.IN_RUN);
		
		tree.deleteKeySearch(deleteWord);
	}

}
