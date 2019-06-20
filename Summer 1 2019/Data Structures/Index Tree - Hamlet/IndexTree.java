package index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

	// This is your root 
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	
	// Make your constructor
	// It doesn't need to do anything
	public IndexTree() {
		this.root = null;
	}
	// complete the methods below
	
	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){
		this.root = add(this.root, word, lineNumber);
	}
	
	
	
	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
		if(root == null) {
			return new IndexNode(word, lineNumber);
		}
		int compResult = word.compareTo(root.word);
		if(compResult == 0) {
			root.list.add(lineNumber);
			root.occurences++;
			//System.out.println("Found another: " + word +"| At Line: " + lineNumber);
			//System.out.println(root.toString());
			return root;
		}else if(compResult < 0) {
			root.left = add(root.left, word, lineNumber);
			return root;
		}else {
			root.right = add(root.right,word,lineNumber);
			return root;
		}
	}
	
	
	
	
	// returns true if the word is in the index
	public boolean contains(String word){
		return contains(this.root,word);
	}
	
	private boolean contains(IndexNode root, String word) {
		if(root == null) {
			return false;
		}
		int compResult = word.compareTo(root.word);
		if(compResult == 0) {
			return true;
		}else if(compResult < 0) {
			return contains(root.left, word);
		}else {
			return contains(root.right, word);
		}
	}
	
	// call your recursive method
	// use book as guide
	public void delete(String word){
		this.root = delete(this.root,word);
	}
	
	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
		if(root == null) {
			return null;
		}
		int compResult = word.compareTo(root.word);
		if(compResult < 0) {
			root.left = delete(root.left,word);
			return root;
		}else if(compResult > 0) {
			root.right = delete(root.right,word);
			return root;
		}else {
			// case 1, root is leaf
            if (root.left == null && root.right == null) {
                return null;
            } // case 2, root has only left child
            else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                IndexNode rootOfLeftSubtree = root.left;
                IndexNode parentOfPredecessor = null;
                IndexNode predecessor = null;

                if (rootOfLeftSubtree.right == null) {
                    root.word = rootOfLeftSubtree.word;
                    root.left = rootOfLeftSubtree.left;
                    return root;
                } else {
                    parentOfPredecessor = rootOfLeftSubtree;
                    IndexNode current = rootOfLeftSubtree.right;
                    while (current.right != null) {
                        parentOfPredecessor = current;
                        current = current.right;
                    }

                    predecessor = current;
                    root.word = predecessor.word;
                    parentOfPredecessor.right = predecessor.left;
                    return root;

                }
            }
		}

	}
	
	
	// prints all the words in the index in order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line
	public String printIndex(){
		StringBuilder str = new StringBuilder();
		orderedIndexTraversal(root,1,str);
		return str.toString();
	}
	
	private String printIndex(IndexNode root) {
		
		if(root == null) {
			return "";
		}
		
		String str = "";
		str += root.left.toString();
		str += root.word;
		str += root.right.toString();
		return str;
	}
	
	private void orderedIndexTraversal(IndexNode root, int depth, StringBuilder str) {// using your example as reference
		for(int i =0; i < depth; i++) {
			str.append("    ");
		}
		if(root == null) {
			str.append("null\n");
		}else {
			str.append((root.toString()));
			str.append("\n");
			orderedIndexTraversal(root.left,depth +1, str);
			orderedIndexTraversal(root.right,depth +1, str);
		}
	}
	
	public static void main(String[] args){
		IndexTree index = new IndexTree();
		String fileName = "pg100.txt";
		int lineNumber = 0;
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				lineNumber++;
				String line = scanner.nextLine();
				//System.out.println(line);
				String[] words = line.split("\\s+");
				for(String word : words){
					word = word.replaceAll(":", "");
					word = word.replaceAll(",", "");
					index.add(word, lineNumber);
					//System.out.println(word);
				}
			}
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// add all the words to the tree
		//index.printIndex(index.root);
		// print out the index
		System.out.println(index.printIndex());
		System.out.println("REMOVING: zounds");
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test removing a word from the index
		index.delete("zounds");
		
		System.out.println(index.printIndex());
		System.out.println("REMOVING: zwagger'd");
		index.delete("zwagger'd");
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(index.printIndex());
	}
}
