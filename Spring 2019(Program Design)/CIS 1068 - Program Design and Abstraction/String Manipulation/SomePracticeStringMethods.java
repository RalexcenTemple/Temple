package stringPractice1068;

public class SomePracticeStringMethods {
	//Reed Ceniviva
	//Temple University
	//2/17/19
	//String Practice
	
    /* 
     * returns true if c is a letter in the word "temple" or false otherwise
     */
    public static boolean inTU(char c) {
    	String tu = "temple";
    	String TU = tu.toUpperCase();
    	int ind = tu.indexOf(c);
    	int IND = TU.indexOf(c);
    	if((ind == (-1))&&(IND == (-1))) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    /*
     * returns the index of the first occurrence of s
     * of a letter in the word "temple" or
     * -1 if s contains no letters in the word "temple"
     */
    public static int indexOfFirstTULetter(String s) {
    	for(int i = 0; i < s.length(); i++) {
    		boolean fnd = inTU(s.charAt(i));
    		if(fnd) {
    			return i;
    		}
    	}
        return -1;
    }

    /*
     * returns the index of the first occurrence of a letter in "temple" in s starting
     * from index startPosition or -1 if there are none at index
     * startPosition or later. Notice that this method has the same name as the
     * previous one, but that it takes a different number of arguments. This is
     * perfectly legal in Java. It's called "method overloading"
     */
    public static int indexOfFirstTULetter(String s, int startPosition) {
    	for(int i = startPosition; i < s.length(); i++) {
    		boolean fnd = inTU(s.charAt(i));
    		if(fnd) {
    			return i;
    		}
    	}
        return -1;
    }

    /*
     * returns the index of the last occurrence of a letter in the word "temple"
     * in s or -1 if s
     * contains none
     */
    public static int indexOfLastTULetter(String s) {
    	int maxFnd = -1;
    	for(int i = 0; i < s.length(); i++) {
    		boolean fnd = inTU(s.charAt(i));
    		if(fnd) {
    			maxFnd = i;
    			}
    		}
        return maxFnd;
    }

    /* returns true if every letter in s is a letter
     * in the word "temple" or false otherwise */
    public static boolean allTempleLetters(String s) {
    	int maxFnd = 0;
    	for(int i = 0; i < s.length(); i++) {
    		boolean fnd = inTU(s.charAt(i));
    		if(fnd) {
    			maxFnd = maxFnd + 1;
    			}
    		}
        if(maxFnd == s.length()) {
        	return true;
        }else {
        	return false;
        }
    }
        
    /* returns true if no letter in s is a letter
     * in the word "temple" or false otherwise */
    public static boolean noTempleLetters(String s) {
    	int maxFnd = 0;
    	for(int i = 0; i < s.length(); i++) {
    		boolean fnd = inTU(s.charAt(i));
    		if(fnd) {
    			maxFnd = maxFnd + 1;
    			}
    		}
        if(maxFnd == 0) {
        	return true;
        }else {
        	return false;
        }
    }

    /*
     * returns a new String which is the same as s, but with all of the letters
     * in the word "temple" removed.
     */
    public static String withoutTULetters(String s) {
    	if(noTempleLetters(s)) {
    		return s;
    	}else {
    		for(int i = 0; i < s.length(); i++) {
    			if(inTU(s.charAt(i))) {
    				String tempf = s.substring(0,i);
    				String tempb = s.substring(i+1,s.length());
    				s = tempf.concat(tempb);
    				i = 0;
    			}
    		}
    		return withoutTULetters(s);
    	}
    }

    /*
     * returns s in reverse. For example, if s is "Temple", the method returns the
     * String "elpmeT"
     */
    public static String reversed(String s) {
    	String result = "";
    	for(int i = (s.length()-1); i >= 0; i--) {
    		result = result + (s.charAt(i));
    	}
        return result;
    }

    /*
     * returns the number of times that n occurs in h. For example, if h is
     * "Mississippi" and n is "ss" the method returns 2.
     */
    public static int numOccurrences(String h, String n) {
    	int maxFnd = 0;
    	for(int i = 0; i < (h.length() - (n.length()-1)); i++) {
    		if((h.substring(i,(i + (n.length())))).toUpperCase().contains(n.toUpperCase())){
    			maxFnd = maxFnd + 1;
    		}
    	}
    	return maxFnd;
    }

    /*
     * returns true if s is the same backwards and forwards and false otherwise
     */
    public static boolean sameInReverse(String s) {
    	if(reversed(s).equals(s)) {
    		return true;
    	}else {
    		return false;
    	}
    }


    //Is this extra credit or something? there's no test cases for this and nearly handed it in because it would seem that since the tests are running completely and correctly that I should be done.
    /*
     * Returns a new String that looks like base appended with suffix. If base
     * already ends with suffix, it returns ba se.
     * 
     * For example, if base is "lightning" and suffix is "bug", returns
     * "lightningbug".
     * 
     * If base is "lightningbug" and suffix is "bug", it also returns
     * "lightningbug".
     */
    public static String appendIfMissing(String base, String suffix) {
    	if(base.substring(base.length()-suffix.length(), base.length()).equals(suffix)){
    		return base;
    	}else {
    		base = base.concat(suffix);
    		return base;
    	}
    }
}