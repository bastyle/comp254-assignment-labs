package ca.centennial.comp254.lab2.exercise1.bastian.bastias;

public class PrimitiveCounting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		int n = 1000;
		System.out.println("Hey - your input is: " + n);
		System.out.println("Hmm.. I'm doing more stuff with: " + n);
		System.out.println("And more: " + n);
		
		//
		
		//running time grows in proportion to the logarithm of the input
		// (in this case, log to the base 2):
		for (int i = 1; i < n; i = i * 2){
		    System.out.println("Hey - I'm busy looking at: " + i);
		}
		
		//n log n is the next class of algorithms. 
		for (int i = 1; i <= n; i++){
		    for(int j = 1; j < n; j = j * 2) {
		        System.out.println("Hey - I'm busy looking at: " + i + " and " + j);
		    }
		}
	}

}
