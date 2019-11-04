import java.util.ArrayList;
import java.util.List;

public class Methods {
	//Uniqueness
	/*
	Write a method called unique() which takes in a List and returns true if all the items in the Listare unique.
	All the items are unique if none of them arethe same. Return false otherwise
	*/
	//O(n^2) operation complexity
	public static <E> boolean unique(List<E> list){
	    for(int i = 0; i < list.size(); i++){//linear (n)
	        for(int j = 0; j < list.size(); j++){ //n^2 {(n)(n)}
	            if(!(i == j)){ //constant
	                if(list.get(i).equals(list.get(j))){ //constant
	                    return false;
	                }
	            }
	        }
	    }
	    return true;
	}

	//All Multiples
	/*
	Write a method namedallMultiples()which takes in aListof integers andanint. 
	The method returns a new List of integers which contains all of the
	numbers from the input list which are multiples of the givenint. 
	For example,if theListis [1,25,2,5,30,19,57,2,25] and 5 was provided,
	the new list shouldcontain [25,5,30,25].
	*/
	//O(n) operation complexity
	public static List<Integer> allMultiples(List<Integer> list, int x){
	    List<Integer> out = new ArrayList<Integer>(); //constant time
	    for(Integer item: list){ //linear time
	        if(((item)%x) == 0){ //constant time
	            out.add(item); // constant time
	        }
	    }
	    return out;
	}

	//All Strings of Size
	/*
	Write a method namedallStringsOfSize()which takes in aList<String>and  anint length.
	The  method  returns  a  newList <String> which
	con-tains  all  the  Strings  from  the  original  list  that  arelengthcharacters  long.For example,
	if the inputs are["I", "like", "to", "eat", "eat", "eat","apples", "and", "bananas"] and 3,
	the new list is["eat", "eat", "eat","and"].
	*/
	//O(n) operation complexity
	public static List<String> allStringsOfSize(List<String> list, int length){
	    List<String> out = new ArrayList<String>();
	    for(String item: list){ //linear
	        if(item.length() == length){ //constant
	            out.add(item);//constant
	        }
	    }
	    return out;
	}

	//isPermutation
	/*
	Write a method calledisPermutaion()which takes in twoListobjects whichcontain the same types. 
	It returnstrueif the Lists are permutations of eachother  and  false  if  not. 
	Two  lists  are  permutations  if  they  contain  the  sameelements, 
	including  the  same  number  of  duplicates, but  they  don’t  have  tocontain the 
	elements in the same order. For example,[1,2, 4]and[2,1,4]are permutations of each other.
	*/
	//O(n^2) operation complexity
	public static <E> boolean isPermutation(List<E> list1, List<E> list2){
	    if(list1.size() == 0){
	        System.out.println("First list is empty, nothing to compare");
	    }else if(list2.size() == 0){
	        System.out.println("The Second list is empty, there is nothing to compare");
	    }else if(list1.size() != list2.size()){
	        System.out.println("The lists are not the same size and so cannot be permutations of each other");
	    }else{
	        for(int i = 0; i < list1.size(); i++){ //(n)
	            E item = list1.get(i);
	            //System.out.println("item: " + item);
	            int countA = 0;
	            int countB = 0;
	            for(int j = 0; j < list1.size(); j++){//(n)
	            	//System.out.println("list2(" + j + "): " + list2.get(j));
	            	//System.out.println("list1(" + j + "): " + list1.get(j));
	                if(item.equals(list2.get(j))){
	                        countB++;
	                }
	                if(item.equals(list1.get(j))){
	                        countA++;
	                }
	                //System.out.println("Count B: " + countB);
	                //System.out.println("Count A: " + countA);
	            }
	            if(countA != countB){
	                return false;
	            }
	        }
	    }
	    return true;
	}


	//exam, two list questions

	//String To List of Words
	/*
	Write a method called stringToListOfWords() which takes in aStringcon-verts 
	it into a list of words. We assumes that each word in the input string
	is separated by whitespace
	f our input String is"Hello, world!", then the output should be["Hello,","world!"]. 
	 For extra credit, sanitize the String, cleaning it up so that we re-move the punctuation 
	 and other extraneous characters such that the output inthe above example would become
	 ["Hello", "world"]This method returnsList<String>.
	*/
	//O(n)  operation complexity
	public static List<String> stringToListOfWords(String str){
		List<String> list = new ArrayList<String>();
		int start = 0;
		int end = 0;
		for(int i = 0; i < str.length(); i++) {//O(n)
			if(str.charAt(i) == ' ') {
				end = i;
				String word = str.substring(start,end);
				list.add(word);
				start=end+1;
				end = start;
			}
			if(i == (str.length()-1)) {//get the last word
				end = i+1;
				String word = str.substring(start,end);
				list.add(word);
			}
		}
		return list;
	}
	//Remove All Instances
	/*
	Write a method called removeAllInstances() which takes in aListand item4.
	The method then proceeds to remove each item in the list that matches the givenitem. 
	For example, if the method is passed theList<Integer> [1, 4, 5, 6,5, 5, 2] and the
	Integer 5, the method removes all5’s from theList.  TheListthen becomes[1, 4, 6, 2]. 
	It should return nothing, since the changestheListit was provided
	*/
	
	public static <E> List<E> removeAllInstances(List<E> list, E item){
		for(int i = 0; i < list.size();i++){//O(n)
			if(list.get(i).equals(item)) {
				list.remove(list.get(i));//O(n)<->0
				i--;
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		//integer lists for testing methods
		//list declaration 
		List<Integer> intList1 = new ArrayList<Integer>();
		List<Integer> intList2 = new ArrayList<Integer>();
		List<Integer> intList3 = new ArrayList<Integer>();
		//list creation
		intList1.add(1);
		intList1.add(2);
		intList1.add(4);
		intList2.add(2);
		intList2.add(1);
		intList2.add(4);
		intList3.add(1);
		intList3.add(1);
		intList3.add(1);
		//Tests
		System.out.println("INTEGER TESTS");
		System.out.println("Integer List 1: " + intList1);
		System.out.println("Integer List 2: " + intList2);
		System.out.println("Integer List 3: " + intList3);
		System.out.println("");
		System.out.println("isPermutation Integer Test1: " + isPermutation(intList1,intList2) +" | (true Expected)");
		System.out.println("isPermutation Integer Test2: " + isPermutation(intList1,intList3) +" | (false Expected)");
		System.out.println("");
		System.out.println("allMultiple Test1: " + allMultiples(intList1,2) + " | ([2, 4] Expected)");
		System.out.println("allMultiple Test2: " + allMultiples(intList3,3) + " | ([] Expected)");
		System.out.println("allMultiple Test3: " + allMultiples(intList1,4) + " | ([4] Expected)");
		System.out.println("");
		System.out.println("unique Integer Test1: " + unique(intList1) + " | (true Expected)");
		System.out.println("unique Integer Test2: " + unique(intList2) + " | (true Expected)");
		System.out.println("unique Integer Test3: " + unique(intList3) + " | (false Expected)");
		System.out.println("");
		System.out.println("removeAllInstances Integer Test1: " + removeAllInstances(intList1,1) + " | ([2, 4] Expected)");
		System.out.println("removeAllInstances Integer Test2: " + removeAllInstances(intList2,4) + " | ([1, 2] Expected)");
		System.out.println("removeAllInstances Integer Test3: " + removeAllInstances(intList3,1) + " | ([] Expected)");
		System.out.println("");
		//string lists for testing methods
		//list declaration 
		System.out.println("STRING TESTS");
		List<String> strList1 = new ArrayList<String>();
		List<String> strList2 = new ArrayList<String>();
		List<String> strList3 = new ArrayList<String>();
		//list creation
		strList1.add("hello");
		strList1.add("world");
		strList1.add("you");
		strList1.add("thank");
		strList2.add("world");
		strList2.add("hello");
		strList2.add("thank");
		strList2.add("you");
		strList3.add("you");
		strList3.add("you");
		strList3.add("you");
		strList3.add("you");
		//Tests
		System.out.println("String List 1: " + strList1);
		System.out.println("String List 2: " + strList2);
		System.out.println("String List 3: " + strList3);
		System.out.println("");
		System.out.println("isPermutation String Test1: " + isPermutation(strList1,strList2) + " | (true Expected)");
		System.out.println("isPermutation String Test2: " + isPermutation(strList1,strList3) + " | (false Expected)");
		System.out.println("");
		System.out.println("allStringsOfSize Test: " + allStringsOfSize(strList1,5) + " | ([world, hello, thank] Expected)");
		System.out.println("allStringsOfSize Test: " + allStringsOfSize(strList1,3) + " | ([you] Expected)");
		System.out.println("allStringsOfSize Test: " + allStringsOfSize(strList1,2) + " | ([] Expected)");
		System.out.println("");
		System.out.println("unique String Test1: " + unique(strList1) + " | (true Expected)");
		System.out.println("unique String Test2: " + unique(strList2) + " | (true Expected)");
		System.out.println("unique String Test3: " + unique(strList3) + " | (false Expected)");
		System.out.println("");
		System.out.println("removeAllInstances String Test1: " + removeAllInstances(strList1,"world") + " | ([hello, thank, you] Expected)");
		System.out.println("removeAllInstances String Test1: " + removeAllInstances(strList2,"thank") + " | ([hello, world, you] Expected)");
		System.out.println("removeAllInstances String Test1: " + removeAllInstances(strList3,"you") + " | ([] Expected)");
		System.out.println("");
		//Create a string for the string to list of words method
		String str = "This is a string of words seperated by spaces and now I'am to add some stuff fire-fighter";
		System.out.println("Long String: " + str);
		System.out.println("");
		System.out.println("stringToListOfWords Test: " + stringToListOfWords(str));
		System.out.println("COMPLETE");
		
	}
}
