package lists;

import java.util.Iterator;

public class MyIteratorDemo implements Iterable<Integer> {

	private Integer cursor;
	
	
	class IntegerIterator implements java.util.Iterator<Integer>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new IntegerIterator();
	}
	
	public static void main(String[] args) {
		My
	}
}
