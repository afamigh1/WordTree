package wordTree.driver;

/*
	BST Implementation taken from:
	http://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
	Search methods taken from:
	http://www.algolist.net/Data_structures/Binary_search_tree/Lookup
	Delete methods taken from:
	http://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
	
	All methods were adapted to the needs of this assignment
*/

class BinarySearchTree {

	class Node {
		String word;
		int wordCount;
		Node left, right;
		boolean unique;

		public Node(String wordKey) {
		    word = wordKey;
		    wordCount = 1;
		    left = right = null;
		    unique = true;
		}
		
		/*
			Implementation of the deleteKeySearch method
			@param String wordKey
			@ret boolean 
		*/
		public boolean deleteSearch(String wordKey) {
			if(wordKey.equals(this.word)) {
				this.decWordCount();
				return true;
			} else if(wordKey.compareTo(this.word) < 0) {
				if(left == null) {
					return false;
				} else {
					return left.deleteSearch(wordKey);
				}
			} else if(wordKey.compareTo(this.word) > 0) {
				if(right == null)
					return false;
				else
					return right.deleteSearch(wordKey);
			}
			return false;
		}
		
		void incWordCount() {
			wordCount += 1;
		}	
	
		void decWordCount() {
			if( wordCount > 0 ) wordCount -= 1;
		}
	}

	/*	BST Data Members	*/
	Node root;
	MyLogger log;
	int totalWords;
	int totalCharacters;
	int totalDistinct;
	

	// Constructor
	BinarySearchTree(MyLogger l) { 
		root = null; 
		totalWords = 0;
		totalCharacters = 0;
		totalDistinct = 0;
		log = l;
		log.writeMessage("BST Constructor", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	/*
		This method calls insertRec which is the recursive
		implementation for inserting into a BST
	*/
	synchronized void insert(String wordKey) {
		root = insertRec(root, wordKey);
	}

	/* 
		A recursive function to insert a new key in BST 
	*/
	synchronized Node insertRec(Node root, String wordKey) {

		/* If the tree is empty, return a new node */
		if (root == null) {
		    root = new Node(wordKey);
		    return root;
		}

		/* Otherwise, recur down the tree */
		if( wordKey.compareTo(root.word) < 0 )
			root.left = insertRec(root.left, wordKey);
		else if( wordKey.compareTo(root.word) > 0 )
			root.right = insertRec(root.right, wordKey);
		else if( wordKey.compareTo(root.word) == 0 ) {	
			//duplicate word so update wordcount
			root.incWordCount();
			root.unique = false;
		}
		return root;
	}
	
	/*
		Method that calls deleteSearch which updates
		nodes word counts based on the key supplied
		@param String wordKey
		@ret none
	*/
	synchronized void deleteKeySearch(String wordKey) {
		root.deleteSearch(wordKey);
	}
	
	/*	This method calls inorderCalc which traverses
		the tree to calculate the # of word etc.
	*/
	synchronized void calculateWordCounts()  {
		inorderCalc(root);
		log.writeMessage("Total Words: " + totalWords, MyLogger.DebugLevel.FROM_RESULTS);
		log.writeMessage("Total Char: " + totalCharacters, MyLogger.DebugLevel.FROM_RESULTS);
		log.writeMessage("Total Distinct: " + totalDistinct, MyLogger.DebugLevel.FROM_RESULTS);
	}

	/*
		Method for calculating the word counts
		including unique words, characters etc.
		@param Node root
		@ret none
	*/
	synchronized void inorderCalc(Node root) {
		if (root != null) {
			totalWords += root.wordCount;
			inorderCalc(root.left);
			if( root.unique == true && root.wordCount != 0) totalDistinct++;
			totalCharacters += (root.word.length() * root.wordCount);
			inorderCalc(root.right);
		}
	}

	/*	
		The following methods are for printing out the tree inorder	
	*/
	synchronized void inorder()  {
		inorderRec(root);
	}
	
	synchronized void inorderRec(Node root) {
		if (root != null) {
		    inorderRec(root.left);
			log.writeMessage(root.word + " -> " + root.wordCount, MyLogger.DebugLevel.FROM_RESULTS);
		    inorderRec(root.right);
		}
	}
}
