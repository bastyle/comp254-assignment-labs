package maps;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
	private MapEntry<K, V>[] table;
	private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

	public ProbeHashMap(float maxLoadFactor) {
		super(maxLoadFactor);
	}

	public ProbeHashMap(int cap) {
		super(cap);
	}

	public ProbeHashMap(int cap, int p) {
		super(cap, p);
	}

	protected void createTable() {
		table = (MapEntry<K, V>[]) new MapEntry[capacity];
	}

	private boolean isAvailable(int j) {
		return (table[j] == null || table[j] == DEFUNCT);
	}

	private int findSlot(int h, K k) {
		int avail = -1;
		int j = h;
		do {
			if (isAvailable(j)) {
				if (avail == -1)
					avail = j;
				if (table[j] == null)
					break;
			} else if (table[j].getKey().equals(k))
				return j;
			j = (j + 1) % capacity;
		} while (j != h);
		return -(avail + 1);
	}

	@Override
	protected V bucketGet(int h, K k) {
		int j = findSlot(h, k);
		if (j < 0)
			return null;
		return table[j].getValue();
	}

	@Override
	protected V bucketPut(int h, K k, V v) {
		int j = findSlot(h, k);
		if (j >= 0)
			return table[j].setValue(v);
		table[-(j + 1)] = new MapEntry<>(k, v);
		n++;
		return null;
	}

	@Override
	protected V bucketRemove(int h, K k) {
		int j = findSlot(h, k);
		if (j < 0)
			return null;
		V answer = table[j].getValue();
		table[j] = DEFUNCT;
		n--;
		return answer;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList();
		for (int h = 0; h < capacity; h++)
			if (!isAvailable(h))
				buffer.add(table[h]);
		return buffer;
	}
	
	public static void main(String[] args) {
		ProbeHashMap<Integer, String> p1 = new ProbeHashMap<>(0.5f);
		
		
	}

}
