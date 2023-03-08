package stacks;

public class JVMStack {

	public static void main(String[] args) {
		//
		int i = 5;
		foo(i);

	}
	
	public static void foo(int j) 
	{
		int k;	
		k = j+1;
		System.out.println("in foo... k="+ k);

		bar(k);
	}
	public static int bar(int m) 
	{ 
		
		System.out.println("in bar... m="+ m);
		return m;
		
	}


}
