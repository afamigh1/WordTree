package wordTree.driver;

import java.util.ArrayList;

public class CreateWorkers {

	FileProcessor fileProc;
	Results res;
	MyLogger log;
	BinarySearchTree tree;

	public CreateWorkers(FileProcessor fp, Results r, MyLogger l, BinarySearchTree t) {
		log = l;
		log.writeMessage("CreateWorkers Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
		fileProc = fp;
		res = r;
		tree = t;
	}
	
	/*
		Method used to create threads to insert words
		into a binary search tree
		@param int num Number of threads to create
		@ret none
	*/
	public void startPopulateWorkers(int num) {
		ArrayList<PopulateThread> arr = new ArrayList<PopulateThread>(1);
		
		for(int i=0; i < num; i++) {
			PopulateThread pop = new PopulateThread(fileProc, res, log, tree);
			arr.add(pop);
		}
		
		for(int i=0; i < arr.size(); i++) {
			arr.get(i).start();
		}
		
		try {
			for(int i=0; i < arr.size(); i++) {
				arr.get(i).join();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/*
		Method used to create threads to delete words
		from a binary search tree
		@param int num Number of threads to create
		@ret none
	*/
	public void startDeleteWorkers(int num, String[] delWords) {
		ArrayList<DeleteThread> arr = new ArrayList<DeleteThread>(1);
		
		for(int i=0; i < num; i++) {
			DeleteThread del = new DeleteThread(fileProc, res, log, tree, delWords[i]);
			arr.add(del);
		}
		
		for(int i=0; i < arr.size(); i++) {
			arr.get(i).start();
		}
		
		try {
			for(int i=0; i < arr.size(); i++) {
				arr.get(i).join();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}


}
