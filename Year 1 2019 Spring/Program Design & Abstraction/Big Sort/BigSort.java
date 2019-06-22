package largesort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class BigSort {
	
		public static void main(String []args)throws FileNotFoundException {
			int chunkSIZE = 512; // change this variable to change the size of the array that the lines are put into/number of lines written to initial sub file
			int count = 0;
			int fcount = 0;
			String[] lines = new String[chunkSIZE];
			
			File bFile = new File("Big.txt");
			Scanner fin = new Scanner(bFile);
			
			while(fin.hasNextLine()) {
				count++;//number of lines so far
				
				String line = fin.nextLine();
				lines[(count%chunkSIZE)] = line; // current folder index = (total lines processed) mod (# of lines per subfile)
				
				if(((count%chunkSIZE) == 0 )) { // when the number of lines per file have been processed
					Arrays.sort(lines); //use the arrays sort method
					System.out.println("Sort Successful");
					String fileName = "temp_0_" + fcount; //create new file name from the format "temp_0_" and the number of times count%chunkSIZE has happened
					System.out.println("Saved to: " + fileName);
					PrintStream linesOut = new PrintStream(new File(fileName)); //create writing access to the new file
					for(int i = 0; i < chunkSIZE;i++) {	//put the lines from the array to the new file
						linesOut.println(lines[i]);
					}
					for(int i = 0; i < chunkSIZE;i++) {//clear out the value of the array for the next one
						lines[i] = "";
					}
						fcount++; //increment the number of times count % chunkSIZE has equalled zero
						linesOut.close(); //close the writing path 
				}
			}
			fin.close();
			int temp = fcount * chunkSIZE; //number of lines from previously saved docs
			temp = count - temp; // number of line in the last file
			
			Arrays.sort(lines);
			System.out.println("Sort Successful");
			
			String fileName = "temp_0_" + fcount;
			System.out.println("Saved to: " + fileName);
			PrintStream linesOut = new PrintStream(new File(fileName));
			
			for(int i = 0; i < chunkSIZE;i++) { // handling the left over lines that didn't fit perfectly to a file
				linesOut.println(lines[i]);
			}
			for(int i = 0; i < chunkSIZE;i++) {
				lines[i] = "";
			}
			
			System.out.println("total line count: " + count); // results of initial file division
			System.out.println("Total number of sub files: " + fcount);
			
			linesOut.close();
			
			int layer = 0; //starting layer of the temp files ( the 0 in temp_0_1)
			int merge = phaseTwo(fcount,layer); //get merge started
			while(merge > 1) { //when merge is equal to 1 that means there is only 1 file left being the reordered file in full
				layer = layer +1; // increment the layer with each pass
				merge = phaseTwo(merge,layer);
			}
		}
		
		public static int phaseTwo(int fcount, int layer) throws FileNotFoundException { //returns the number of files created from the pass of merge sorting 
			int nCount = 0; //counter for file creation
			
			if(fcount%2 == 0) {
				for(int i = 0; i < fcount-1; i+=2) {
					String f1Name = "temp_" + layer + "_" + i;
					String f2Name = "temp_" + layer + "_" + (i+1);
					
					File s1File = new File(f1Name);
					File s2File = new File(f2Name);
					
					Scanner s1Lines = new Scanner(s1File);
					Scanner s2Lines = new Scanner(s2File);
					
					String lgrFileName = "temp_" + (layer+1) + "_" + nCount;
					System.out.println("merging to file: " + lgrFileName);
					PrintStream larger = new PrintStream(new File(lgrFileName));
					
					String line1 = s1Lines.nextLine();//get the first lines of the files to be merged
					String line2 = s2Lines.nextLine();
					
					while(s1Lines.hasNextLine() && s2Lines.hasNextLine()){//while the files both have lines left
						int temp = mergesort(line1,line2,larger);//merge them
						
						if( temp == 0) {
							line1 = s1Lines.nextLine();//change the value of the string variable as they are added to the larger file from their respective smaller files
							line2 = s2Lines.nextLine();
						}else if(temp == 1) {
							line2 = s2Lines.nextLine();
						}else if(temp == 2) {
							line1 = s1Lines.nextLine();
						}else {
							System.out.println("merge sort failure");
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
					}
					if(s1Lines.hasNextLine() && !(s2Lines.hasNextLine())) {//take care of when the files have uneven number of lines
						while(s1Lines.hasNextLine()) {
							larger.println(s1Lines.nextLine());
						}
					}else if(s2Lines.hasNextLine() && !(s1Lines.hasNextLine())) {
						while(s2Lines.hasNextLine()) {
							larger.println(s2Lines.nextLine());
						}
					}//close opened files and delete temp files
					larger.close();
					s1Lines.close();
					s2Lines.close();
					s1File.delete();
					s2File.delete();
					
					nCount++;//new subfile total number
				}
			}else { // do it all over again but for odds
				for(int i = 0; i < fcount-2; i+=2) {
					String f1Name = "temp_" + layer + "_" + i;
					String f2Name = "temp_" + layer + "_" + (i+1);
					
					File s1File = new File(f1Name);
					File s2File = new File(f2Name);
					
					Scanner s1Lines = new Scanner(s1File);
					Scanner s2Lines = new Scanner(s2File);
					
					String lgrFileName = "temp_" + (layer+1) + "_" + nCount;
					System.out.println("merging to file: " + lgrFileName);
					PrintStream larger = new PrintStream(new File(lgrFileName));
					
					String line1 = s1Lines.nextLine();
					String line2 = s2Lines.nextLine();
					
					while(s1Lines.hasNextLine() && s2Lines.hasNextLine()){
						int temp = mergesort(line1,line2,larger);
						if( temp == 0) {
							line1 = s1Lines.nextLine();
							line2 = s2Lines.nextLine();
						}else if(temp == 1) {
							line2 = s2Lines.nextLine();
						}else if(temp == 2) {
							line1 = s1Lines.nextLine();
						}else {
							System.out.println("merge sort failure");
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
					}
					if(s1Lines.hasNextLine() && !(s2Lines.hasNextLine())) {
						while(s1Lines.hasNextLine()) {
							larger.println(s1Lines.nextLine());
						}
					}else if(s2Lines.hasNextLine() && !(s1Lines.hasNextLine())) {
						while(s2Lines.hasNextLine()) {
							larger.println(s2Lines.nextLine());
						}
					}
					larger.close();
					s1Lines.close();
					s2Lines.close();
					s1File.delete();
					s2File.delete();
					
					nCount++;//new subfile total number
				}
				
				String ffName = "temp_" + (layer + 1) + "_" + nCount; //move the last file from an odd number of files up to the next layer until there is an even number of files as a result.
				PrintStream oddFileOut = new PrintStream(new File(ffName));
				System.out.println(ffName + ": CREATED");
				
				String lfName = "temp_" + (layer) + "_" + (fcount-1);
				File lFile = new File(lfName);
				nCount++;
				Scanner fsLines = new Scanner(lFile);
				
				while(fsLines.hasNextLine()) {
					oddFileOut.println(fsLines.nextLine());
				}
				
				oddFileOut.close();
				fsLines.close();
				lFile.delete();
			}
			return (nCount);
		}
		
		public static int mergesort(String line1, String line2, PrintStream larger) { //handle the comparisons and writing to the larger file
			if((line1.compareTo(line2)) == 0) {//tells the calling function what the result was so it knows what variables to change
				larger.println(line1);
				larger.println(line2);
				return 0;
			} else if((line1.compareTo(line2)) > 0) {
				larger.println(line2);
				return 1; // report that the second string was smaller than the first
			} else if((line1.compareTo(line2)) < 0) {
				larger.println(line1);
				return 2; // report that the first String was smaller than the second
			}
			return 3;
		}
		
}
