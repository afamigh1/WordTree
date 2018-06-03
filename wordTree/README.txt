Compile: ant -buildfile src/build.xml all

Run: ant -buildfile src/build.xml -Darg0=<input file> -Darg1=<output file> -Darg2=<NUM_THREADS> -Darg3=<delete words> -Darg4=<DEBUG_LEVEL>

BST Implementation taken from: http://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

For the implementation of inserting into the tree I chose a BST because insertion was my main focus
and BST insertion is O(n) where n is the height but I assumed this would never happen because 
there most likely won't be files of completely unique words.
For the Results data structures I stored my results in an ArrayList in case we needed to change
the implementation to display more types of results, different than the three we needed for this
assignment and since it was only a few this time, we don't lose much time using an ArrayList over an array.

"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating I will have to sign an official form that I have cheated and that this form will be stored in my official university record. I also understand that I will receive a grade of 0 for the involved assignment for my first offense and that I will receive a grade of F for the course for any additional offense."

Date: 11/7/2017
