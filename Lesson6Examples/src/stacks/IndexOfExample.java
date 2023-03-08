package stacks;

public class IndexOfExample
{  
	public static void main(String args[])
	{  
		String s1 = "this is index of example";   
		//passing char value  
		int index1 = s1.indexOf('s');//returns the index of s char value  
		System.out.println(index1); //3
		//passing char value and index
		int index2 = s1.indexOf('s',5); //returns the index after 5th index
		System.out.println(index2); //6
		//
		String s2 = "javatpoint";		
		System.out.println(s2.substring(2,4)); //returns va  
		System.out.println(s2.substring(2)); //returns vatpoint

	} //main
}  