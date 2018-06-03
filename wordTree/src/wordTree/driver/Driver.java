package wordTree.driver;

import java.util.ArrayList;

public class Driver {
	
	public static void main(String[] args) {
		int i, j;
		String line;
		
		try {
			if(args.length != 5) { 
				System.err.println("Please enter 5 arguments.\n");
				System.exit(0);
			}
			
			int NUM_THREADS = Integer.parseInt(args[2]);
			if( NUM_THREADS < 1 || NUM_THREADS > 3 ) {
				System.out.println("Num of threads needs to be between 1 and 3 (inclusive)");
				System.exit(0);
			}
			
			String deleteArray[] = new String[3];
			String delims = "[ ]+";
			String deleteWords;
			
			deleteWords = args[3];
			deleteArray = deleteWords.split(delims);
			
			if( deleteArray.length != 3 ) {
				System.out.println("Number of words entered to delete incorrect");
				System.exit(0);
			}
			
			int DEBUG_LEVEL = Integer.parseInt(args[4]);
			if( DEBUG_LEVEL < 0 || DEBUG_LEVEL > 4 ) {
				System.out.println("Debug value needs to be between 0 and 4 (inclusive)");
				System.exit(0);
			}
			
			MyLogger logger = new MyLogger();
			logger.setDebugValue(DEBUG_LEVEL);
			
			FileDisplay fileDisplay = new FileDisplay(args[1], logger);
			
			FileProcessor fileProc = new FileProcessor(args[0], logger);
			Results results = new Results(logger);
			
			BinarySearchTree tree = new BinarySearchTree(logger);
			
			CreateWorkers createWorkers = new CreateWorkers(fileProc, results, logger, tree);
			createWorkers.startPopulateWorkers(NUM_THREADS);
			
			createWorkers.startDeleteWorkers(NUM_THREADS, deleteArray);
			
			tree.calculateWordCounts();
			
			results.writeSchedulesToFile(fileDisplay, tree);
			
			/*	Debug	
			results.writeToScreen(tree);
			*/
			
			
			fileDisplay.closeOutput();
			fileProc.closeInput();
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Warning: " 
			+ "ArrayIndexOutOfBoundsException.");	
			System.exit(0);	
		} catch(NumberFormatException e) {
			System.out.println("Warning: " 
			+ "NumberFormatException. Some input given wasn't an integer.");
			System.exit(0);
		} finally {
			
		}
	
	}

}
