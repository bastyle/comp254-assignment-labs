package ca.centennial.comp254.lab2.exercise1.bastian.bastias;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClassDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arrayTest= {1,4,5,2,7,8,9,7,5,6};
		long startTime=System.nanoTime();
		sort(arrayTest);
		long endTime=System.nanoTime();
		System.out.println("time sort 1: "+(endTime-startTime));
		
		double[] arrayTest2= new double[50];
		startTime=System.nanoTime();
		for(int i=0;i<50;i++) {
			arrayTest2[i]=new Random().nextDouble();
		}
		sort(arrayTest);
		endTime=System.nanoTime();
		long convert = TimeUnit.SECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS);
		System.out.println("time sort 2: "+(endTime-startTime));
		
	}
	
	private static void sort(double array[]) {
		Arrays.sort(array);
	}

}
