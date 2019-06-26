package circularlinkedlist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class CircularLinkedList<E> implements Iterable<E> {
	// Your variables
	Node<E> head;
	Node<E> tail;
	int size;  // BE SURE TO KEEP TRACK OF THE SIZE
	// implement this constructor
	public CircularLinkedList() {
		this.head = new Node<E>(null);
		this.tail = new Node<E>(null);
		this.size = 0;
	}
	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	// be sure to handle out of bounds cases
	private Node<E> getNode(int index ) {
		Node<E> current = head;
		if(index > size || index < 0) {
			index = (28+index)%(this.size);
		}
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
	}
	// attach a node to the end of the list
	public boolean add(E item) {
		this.add(size,item);
		return false;

	}
	// Cases to handle
	public void add(int index, E item){
		if(index < 0 || index > size) {	//out of bounds
			index = Math.abs(index % this.size);//whithen the base of the list
		}
		Node<E> NODE = new Node<E>(item);
		if(size == 0) {
			tail = NODE;
			tail.next = NODE;
			tail.next = tail;
			head = NODE;
			size++;
		}
		else if(index == 0) {
			NODE.next = tail.next;
			tail.next = NODE;
			head = NODE;
			size++;
		}
		else if(index == (size)) { 	//size is the number of elements in the list, index is the index of those elements
			//System.out.println("ADDING NEW TAIL: OLD TAIL: " + tail.item);
			tail.next = NODE;
			NODE.next = head;
			tail = NODE;
			//System.out.println("ADDING NEW TAIL: NEW TAIL: " + tail.item);
			size++;
			//System.out.println(this.toString());
		}
		else {
			Node<E> before = getNode(index-1);
			NODE.next = before.next;
			before.next = NODE;
			size++;
		}
	}
	// remove must handle the following cases
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing 
	// removing any other node
	// REMEMBER TO DECREMENT THE SIZE
	public E remove(int index) {
		E toReturn = null;
		//System.out.println(this.toString());
		//System.out.println("SIZE: " + size + "|INDEX: " + index);
		if(index < 0 || index > size) {	//out of bounds
			//System.out.println("INDEX ADJUSTMENT FOR REMOVAL: OLD INDEX: " + index);
			//System.out.println("                           CURRENT SIZE: " + size);
			index = ((index % this.size));//whithen the base of the list
			//System.out.println("INDEX ADJUSTMENT FOR REMOVAL: NEW INDEX: " + index);
		}      
		if(size == 1) {//hit for emptying a list
			toReturn = head.item;
			tail = head;
			head = null;
			size--;
		}
		else if(index == 0) {//Hit for getting the head
			toReturn = head.item;
			head = head.next;
			tail.next = tail.next.next;
			size--;
		}
		else if(index == size) { //Hit for getting the bottom card 
			Node<E> NODE = this.getNode(size-2);//getting the node before the tail
			toReturn = tail.item;				//removing the tail so we return the tail item
			NODE.next = head;					//NODE.next to take over the end connect from the tail
			tail = NODE;						//set tail to NODE so that the old tail is collected as dangling memory
			size--;								//decrease size
		}
		else {
			Node<E> NODE = this.getNode(index-1);
			toReturn = NODE.next.item;
			NODE.next = NODE.next.next;
			size--;
		}
		if(size == 1) {
			tail = head;
		}
		return toReturn;
	}
	// Turns your list into a string
	// Useful for debugging
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.item.toString();
			
		}
		else{
			do{
				result.append(current.item);
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}
	//
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}
	// provided code for different assignment
	// you should not have to change this
	// change at your own risk!
	// this class is not static because it needs the class it's inside of to survive!
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}
		
		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			// TODO Auto-generated method stub
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.item;
	
		}
		
		// removed the last node was visted by the .next() call 
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}
		
	}
	// It's easiest if you keep it a singly linked list
	// SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
	private static class Node<E>{

		E item;
		Node<E> next;
		
		public Node(E item) {
			this.item = item;
		}
		
	}
	
	
	//DONT TOUCH ANYTHING ABOVE HERE UNLESS CREATING NEW THINGS
	public static void main(String[] args){
		
		//RANDOMLY GENERATE ORDERS OF 26 CARDS AND 2 JOKERS AND PUT THEM THROUGHT THE KEYSTREAM PROCESS

			final int SIZE = 4*4*4*4;
			int[] KeyStreams = new int[SIZE + 1];
			for(int i = 0; i < (SIZE);i++) {
				CircularLinkedList<Integer> Deck = createDeck();
		    	//System.out.println("Deck Created: " + Deck.toString());
		    	int key = solitair(Deck);
		    	System.out.println("Keystream: " + key);
		    	KeyStreams[i] = key;
		    	int sum = 0;
		    	System.out.println();
		    	int count = 0;
		    	for(int j : KeyStreams) {
		    		if(j != 0) {
		    			count++;
		    		}
		    		System.out.print(j + ", ");
		    		sum += j;
		    	}
		    	System.out.println();
		    	System.out.println("Size: " + count);
		    	System.out.println("SUM: " + sum);
			}
				
			try {
				TimeUnit.MILLISECONDS.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//TEST THE KEYSTREAM PROCESS AGAINST KNOWN NUMBER ORDERS
		///*
		CircularLinkedList<Integer> Deck0 = createDeck(0);
		CircularLinkedList<Integer> Deck1 = createDeck(1);
		CircularLinkedList<Integer> Deck2 = createDeck(2);
		CircularLinkedList<Integer> Deck3 = createDeck(3);
		CircularLinkedList<Integer> Deck4 = createDeck(4);
		CircularLinkedList<Integer> Deck5 = createDeck(5);
		CircularLinkedList<Integer> Deck6 = createDeck(6);
		int[] keyStream = new int[7];
		int[] correct = new int[] {6,8,15,12,12,12,13};
		String[] results = new String[7];
		for(int i = 0 ; i < keyStream.length; i++) {
			keyStream[i] = solitair(createDeck(i));
			results[i] = ("TEST: " + i + "|RESULT: " + keyStream[i] +"|CORRECT: " + (keyStream[i] == correct[i]) +"|");
		}
		for(int i = 0 ; i < results.length; i++) {
			System.out.println(results[i]);
		}
		//*/
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///*
		char[] code = new char[] {'a','l','o','n','g','t','i','m','e','a','g','o','i','n','a','g','a','l','e','x','y','f','a','r','a'};
		//char[] code = new char[] {'A','L','O','N','G','T','I','M','E','A','G','O','I','N','A','G','A','L','E','X','Y','F','A','R','A'};
		int[] CODE = new int[code.length];
		CircularLinkedList<Integer> Deck = createDeck();
		CircularLinkedList<Integer> Copy = copyDeck(Deck);
		for(int i = 0; i < CODE.length;i++) {
			CODE[i] = solitair(Deck);
		}
		for(int j = 0; j < code.length;j++) {
			code[j] = Encryption.encryptChar(code[j], CODE[j]);
		}
		System.out.println("");
		for(int n = 0; n < code.length;n++) {
			System.out.print( code[n] + ", ");
		}
		System.out.println("");
		for(int m = 0; m < CODE.length;m++) {
			System.out.println("Letter: " + code[m]);
			System.out.println("   KEY: " + CODE[m]);
			code[m] = Encryption.decryptChar(code[m], CODE[m]);
			System.out.println("Changed Letter: " + code[m]);
		}
		for(int q = 0; q < code.length;q++) {
			System.out.print( code[q] + ", ");
		}
		
		
		//*/
		
		
		
	}

	
	
		public static int solitair(CircularLinkedList<Integer> deck) {
			//System.out.println("|     START ENCRYPTION       |");
			CircularLinkedList<Integer> temp = new CircularLinkedList<Integer>();
			temp = copyDeck(deck);
			//deckCheck(deck);
			//System.out.println("    			CURRENT DECK: " + deck.toString());
			temp = deck;
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("|**********BEGIN*************|");
			System.out.println("|    A AND B MOVE START      |");
			deck = AandBMove(deck);
			deckCheck(deck);
			System.out.println("|    A AND B MOVE COMPLETE   |");
			System.out.println("|CURRENT DECK: " + deck.toString());
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("|     TRIPLE CUT START       |");
			deck = TripleCut(deck);
			deckCheck(deck);
			System.out.println("|     TRIPLE CUT COMPLETE    |");
			System.out.println("|CURRENT DECK: " + deck.toString());
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("|BOTTOM CARD SHUFFLE START   |");
			deck = bottomCardShuffle(deck);
			deckCheck(deck);
			System.out.println("|BOTTOM CARD SHUFFLE COMPLETE|");
			System.out.println("|CURRENT DECK: " + deck.toString());
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("|   TOP CARD VETO START      |");
			int Key = topCardVeto(deck);
			deckCheck(deck);
			System.out.println("|   TOP CARD VETO COMPLETE   |");
			System.out.println("|CURRENT DECK: " + deck.toString());
			temp = copyDeck(deck);
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int out;
			if(Key > 26) {
				out = 0;
				out = solitair(deck);
				System.out.println("RETURNED FROM SOLITAIR: " + out);
				return out;
			}else {
				return Key;
			}
		}
		
		
		
		public static CircularLinkedList<Integer> AandBMove(CircularLinkedList<Integer> deck){
			//JOKER A AND B MOVEMENTS:
			//System.out.println("JOKER A AND B MOVEMENT STARTS");
			//find JOKER A: 27
				//deckCheck(deck);
				System.out.println(deck.toString());
				int JALoc = findCard(deck,27);
				deck = swapPair(deck, JALoc);
			//find JOKER B: 28
				int JBLoc = findCard(deck,28);
				int JBMove = JBLoc +2;
				deck = swap(deck,JBLoc,((JBMove)%deck.size));
				System.out.println(deck.toString());
			//	Swap joker A with the card that comes after it
				

			//	Swap joker B with the card that comes after it twice
				
				return deck;
		}

		public static CircularLinkedList<Integer> TripleCut(CircularLinkedList<Integer> deck){
			//TRIPLE CUT
			int temp1 = findCard(deck,27);
			int temp2 = findCard(deck,28);
			int[] jokers = new int[2];
			if(temp1 > temp2) { //put in order of appearance 
				jokers[1] = temp1;
				jokers[0] = temp2;
			}else {
				jokers[0] = temp1;
				jokers[1] = temp2;
			}
			CircularLinkedList<Integer> front = copyDeck(deck);
			//deckCheck(front);
			CircularLinkedList<Integer> back = copyDeck(deck);
			//deckCheck(back);
			CircularLinkedList<Integer> mid = copyDeck(deck);
			//deckCheck(mid);
			//deckCheck(deck);
			CircularLinkedList<Integer> frontCut = subDeck(front,0 , jokers[0]);//	from the head to the first joker found
			//deckCheck(deck);
		//	swap with 
			deck = subDeck(mid, jokers[0],jokers[1]+1);
			CircularLinkedList<Integer> BackCut = subDeck(back,((jokers[1])+1),back.size);//	from the second joker found to the tail
			//deckCheck(deck);
			System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(" FRONT CUT OF THE TRIPLE CUT: " + frontCut.toString());
			//System.out.println(" FRONT CUT OF THE TRIPLE CUT INFO:");
			//deckCheck(frontCut);
			System.out.println(" MIDDLE REMAINDER TRIPLE CUT: " + deck.toString());
			//System.out.println(" MIDDLE CUT OF THE TRIPLE CUT INFO:");
			//deckCheck(deck);
			System.out.println("  BACK CUT OF THE TRIPLE CUT: " + BackCut.toString());
			//System.out.println(" BACK CUT OF THE TRIPLE CUT INFO:");
			//deckCheck(BackCut);
			System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------");
			BackCut = deckCombine(BackCut, deck); //swapping position while recombining
			//deckCheck(BackCut);
			BackCut = deckCombine(BackCut,frontCut);
			//deckCheck(BackCut);
			deck = BackCut; //set the completed deck back to the original variable for simplicity
			//deckCheck(deck);
			return deck;
		}
		
		public static CircularLinkedList<Integer> bottomCardShuffle(CircularLinkedList<Integer> deck){
			//BOTTOM CARD SHUFFLE
		//	remove and hold bottom card, get value of bottom card
			CircularLinkedList<Integer> last = new CircularLinkedList<Integer>();
			CircularLinkedList<Integer> part = new CircularLinkedList<Integer>();
			//CircularLinkedList<Integer> copy = copyDeck(deck);
			
			last.add(deck.remove(deck.size));
			int botCardVal = last.head.item;
			//System.out.println("LAST: " + last.toString());
			//System.out.println("INIT: " + deck.toString());
			//System.out.println("BOTCARDVAL: " + botCardVal);
			for(int i = 0 ; i < botCardVal && i < 27; i++) {
				part.add(deck.remove(0));
				//System.out.println("DECK: " + deck.toString());
				//System.out.println("PART: " + part.toString());
				//System.out.println("DECK INFO: ");
				//deckCheck(deck);
			}
			
			//System.out.println("PART: " + part.toString());
			//System.out.println("PART INFO: ");
			//deckCheck(part);
			//System.out.println("DECK: " + deck.toString());
			//System.out.println("DECK INFO: ");
			//deckCheck(deck);
			
			//copy = subDeck(copy,botCardVal,copy.size-1);
			
			//System.out.println("");
			System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("    BOTTOM CARD VALUE: " + last.toString());
			System.out.println("BOTTOM CARD PARTITION: " + part.toString());
			System.out.println("       DECK LEFTOVERS: " + deck.toString());
			System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------");
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			//System.out.println("DECK LEFTOVERS INFO: ");
			//deckCheck(deck);
			
			//System.out.println("BOTTOM CARD PARTITION INFO:");
			//deckCheck(part);
			
		//	insert those cards at the tail of the deck
			deck = deckCombine(deck,part);
			//System.out.println(" BTTM CRD PRTTION CMBND:W/OUT LSTVAL: " + deck.toString());
			//System.out.println(" BTTM CRD PRTTION CMBND:W/OUT LSTVAL: ");
			//deckCheck(deck);
			
		//	insert the bottom card back at the tail of the deck
			deck.add(last.head.item);
			//System.out.println(" BTTM CRD PRTTION CMBND: W/ LAST VAL: " + deck.toString());
			//System.out.println(" BTTM CRD PRTTION CMBND: W/ LAST VAL:");
			//deckCheck(deck);
			return deck;
		}
		
		public static int topCardVeto(CircularLinkedList<Integer> deck){
			//TOP CARD VETO
		//	using the value of the card at the top of the deck
			int topVal = deck.head.item;
			System.out.println("           TOP CARD VALUE: " +topVal);
			//                  ______________________________
			int TopVal = deck.tail.item;
			int preTopVal = deck.getNode(deck.size - 2).item;
			
		//	count down that many cards
		//	record the value of the card after the card you land upon
			int topValCardVal = (int) deck.getNode(topVal-1).next.item;
			System.out.println("           KEY CARD VALUE: " + topValCardVal);
		//	if the card is a joker, restart this process
			//if(preTopVal == 27 && TopVal == 28) {
				//return solitair(createDeck());
			//	return -1;
			//}
			if(topValCardVal > 26) {
				System.out.println("*******************************************");
				System.out.println("******TOP CARD VETO USED: BACK TO TOP******");
				System.out.println("*******************************************");
				return solitair(deck);
			}else {
				return (int)topValCardVal;
			}
		//	otherwise, this value is a value for the keystream
		}

		
		public static void deckCheck(CircularLinkedList<Integer> deck) {
			System.out.println("______________________________");
			System.out.println("|DECK HEAD VALUE : " + deck.head.item + "       ||");
			System.out.println("|DECK TAIL VALUE : " + deck.tail.item + "       ||");
			int JALoc = deck.findCard(deck, 27);
			System.out.println("|JOKER A POSITION: " + JALoc + "       ||");
			int JBLoc = deck.findCard(deck, 28);
			System.out.println("|JOKER B POSITION: " + JBLoc + "       ||");
			System.out.println("|    CURRENT SIZE: " + deck.size + "       ||");
			System.out.println("******************************");
			
		}

		
		
		public static CircularLinkedList<Integer> copyDeck(CircularLinkedList<Integer> A) {
			CircularLinkedList<Integer> B = new CircularLinkedList<Integer>();
			//System.out.println("COPYING DECK");
			Node<Integer> currentA = A.head;
			for(int i = 0; i < A.size;i++) {
				B.add(currentA.item);
				currentA = currentA.next;
				//System.out.println("ROUND: " + i +"| A: " + A.toString());
				//System.out.println("ROUND: " + i +"| B: " + B.toString());
			}
			
			return B;
		}



		public static CircularLinkedList<Integer> subDeck(CircularLinkedList<Integer> deck, int a, int b){
			CircularLinkedList<Integer> temp = new CircularLinkedList<Integer>();// deck to put the subdeck into and return
			CircularLinkedList<Integer> copy = new CircularLinkedList<Integer>();
			copy = copyDeck(deck);
			if(a>b) {
				b += 28;
			}
			
			int j=a;
			for(int i = a; i < b; i++) {//(avg)O((1/3)*(28)
				temp.add(deck.remove(j));
				//System.out.println("REMOVING FROM DECK AT: " + j +"| FOR THE: " + (i-j) + "th TIME");
				//System.out.println("   ORIGINAL DECK: " + deck.toString());
				//System.out.println("NEW SUBDECK DECK: " + temp.toString());
			}
			return temp;
			
		}
		
		public static CircularLinkedList<Integer> deckCombine(CircularLinkedList<Integer> A,CircularLinkedList<Integer> B) {
			//Adding deck B to Deck A Sequentially from head to tail
			if(A.head == null) {
				//System.out.println("A is empty, setting it to B");
				A = copyDeck(B);
				return A;
			}else if(B.head == null) {
				//System.out.println("B is empty, nothing to add to A");
				return A;
			}else {
				int size = B.size;
				for(int i = 0; i < size; i++) { //O(B.size) B=(avg)((1/3)*28)
					A.add(B.remove(0));
				}
				return A;
			}
		}
		
		public static int findCard(CircularLinkedList<Integer> deck, int val) {
			int toReturn = -1;
			Node<Integer> card = deck.head;
			for(int i = 0; i < deck.size;i++) {//O(deck.size) complexity
				if((int)card.item == val) {
					toReturn = i;
					break; //at this point JokerA is the joker card im looking for and i have the index it is at
				}else {
					card= card.next;
				}
			}
			return toReturn;
		}
		
		public static CircularLinkedList<Integer> swap(CircularLinkedList<Integer> deck, int a, int b){ //b exclusive
			//System.out.println("SWAP");
			if(a > b) {
				//System.out.println("B changed from: " + b);
				b += deck.size;
				//System.out.println("B changed to  : " + b);
			}
			if((b-1) == a) {
				return swapPair(deck,a);
			}else {
				for(int i = a; i < b;i++) {
					deck = swapPair(deck,i);
				}
				return deck;
			}
		}
		
		public static CircularLinkedList<Integer> swapPair(CircularLinkedList<Integer> deck, int a){
			//System.out.println("SWAPPAIR: " + a);
			if(a >= deck.size) {
				a = a%deck.size;
				//System.out.println("A resized: " + a);
			}
			//if(a < 0 )
			if(a == 0) {
				
				Node<Integer> Card = deck.head;
				Node<Integer> CardBefore = deck.tail;
				Node<Integer> b = deck.head.next;
				/*
				Card.next = Card.next.next;
				b.next = Card;
				CardBefore.next = b;
				deck.head = Card;
				*/
				///*
				//CircularLinkedList<Integer> temp = new CircularLinkedList<Integer>();
				//temp.add(Card.item);
				Card.next = b.next;
				b.next = Card;
				deck.tail.next = b;
				deck.head = b;
				//*/
				return deck;
				
			}else if(a == deck.size-1){
				
				int NB4 = a -1;
				
				
				//CircularLinkedList<Integer> something = new CircularLinkedList<Integer>();
				
				//something.add(deck.remove(a));
				//deckCombine(something,deck);
				///*
				//deck.add(a+1,something.getNode(0).item);
				Node<Integer> preTail = deck.getNode(NB4);
				Node<Integer> Tail = deck.getNode(a);
				Node<Integer> Head = deck.getNode(0);
				Node<Integer> postHead = deck.getNode(1);
				//swapping the tail and the head
				preTail.next = Head;
				Head.next = Tail;
				Tail.next = postHead;
				deck.head = Tail;
				deck.tail = Head;

				//*/
				return deck;
			}else {
				
				int NB4 = a -1;
				int next = a + 1;
				
				Node<Integer> Card = deck.getNode(a);
				Node<Integer> CardBefore = deck.getNode(NB4);
				Node<Integer> b = deck.getNode(next);

				CardBefore.next = b;
				Card.next = b.next;
				b.next = Card;
				
				return deck;
			}
		}
		
		public static List<Integer> shuffle(List<Integer> list) {
			List<Integer> temp = new ArrayList<Integer>();
			int size = list.size();
			for(int i = 0; i < size;i++) {
				double j = (Math.random()*list.size());
				temp.add(list.remove((int)j));
			}

			return temp;
		}
		
		public static CircularLinkedList<Integer> createDeck(){
			CircularLinkedList<Integer> Deck = new CircularLinkedList<Integer>();
			List<Integer> nums = new ArrayList<Integer>();
			for(int i = 1; i < 29; i++) {
				nums.add(i);
			}
			//System.out.println(nums);
			nums = shuffle(nums);
			//System.out.println(nums);
			for(Integer item: nums) {
				Deck.add(item);
			}
			//System.out.println(Deck.toString());
			return Deck;
		}
		
		public static CircularLinkedList<Integer> createDeck(int errorType){
			//26 ==> 19 ==> 15 ==> 9 ==> 25 ==> 5 ==> 10 ==> 21 ==> 11 ==> 27 ==> 7 ==> 13 ==> 6 ==> 18 ==> 17 ==> 22 ==> 4 ==> 12 ==> 8 ==> 14 ==> 3 ==> 23 ==> 2 ==> 24 ==> 1 ==> 20 ==> 16 ==> 28 ==> 
			CircularLinkedList<Integer> Deck = new CircularLinkedList<Integer>();
			int[][] errorArrays = new int[7][28];
			int[] error0 = new int[] {26,19,15,9,25,5,10,21,11,27,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,16,28};// ERROR TEST0: working = 6
			errorArrays[0] = error0;
			int[] error1 = new int[] {26,19,15,9,25,5,10,21,11,28,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,16,27};// ERROR TEST1: null pointer exception from removing during bottom card shuffle
			errorArrays[1] = error1;
			int[] error2 = new int[] {27,19,15,9,25,5,10,21,11,28,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,16,26};// ERROR TEST2: working = 15
			errorArrays[2] = error2;
			int[] error3 = new int[] {26,19,15,9,25,5,10,21,27,16,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,28,11};// ERROR TEST3: working = 12
			errorArrays[3] = error3;
			int[] error4 = new int[] {27,19,15,9,25,5,10,21,26,16,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,11,28};// ERROR TEST4: null pointer exception from removing during bottom card shuffle
			errorArrays[4] = error4;
			int[] error5 = new int[] {27,19,15,9,25,5,10,21,26,16,7,13,6,18,17,22,4,12,8,14,3,23,2,24,1,20,28,11};// ERROR TEST5: working = 12
			errorArrays[5] = error5;
			int[] error6 = new int[] {21,19,15,9,25,5,10,27,26,16,7,13,6,18,17,22,4,12,8,28,3,23,2,24,1,20,14,11}; // BASE TEST(6):  working = 13
			errorArrays[6] = error6;
			
			
			List<Integer> nums = new ArrayList<Integer>();
			for(int i = 0; i < errorArrays.length; i++) {
				if(i == errorType) {
					for(int j : errorArrays[i]) {
						nums.add(j);
					}
				}
			}
			if(nums.isEmpty()) {
				int index = (int)Math.random()*errorArrays.length;
				for(int j : errorArrays[index]) {
					nums.add(j);
				}
			}
			for(Integer item: nums) {
				Deck.add(item);
			}
			return Deck;
		}

	
}