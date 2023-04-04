import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** An implementation of a sorted map using a binary search tree. */
public class TreeMap1<K,V> extends AbstractSortedMap<K,V> { 
	// To represent the underlying tree structure, we use a specialized subclass of the

	// LinkedBinaryTree class 
	protected LinkedBinaryTree<Entry<K,V>> tree = new LinkedBinaryTree<Entry<K,V>>( );

	/** Constructs an empty map using the natural ordering of keys. */
	public TreeMap1( ) 
	{  
		super( ); // the AbstractSortedMap constructor
		tree.addRoot(null); // create a sentinel leaf as root
	}
	/** Constructs an empty map using the given comparator to order keys. */
	public TreeMap1(Comparator<K> comp) 
	{  
		 super(comp); // the AbstractSortedMap constructor
		 tree.addRoot(null); // create a sentinel leaf as root
	} 
	/**
	   * Returns the number of entries in the map.
	   * @return number of entries in the map
	   */
	@Override
	public int size() {
	    return (tree.size() - 1) / 2;        // only internal nodes have entries
	}
	/** Utility used when inserting a new entry at a leaf of the tree */
	private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) 
	{  
		tree.set(p, entry); // store new entry at p
		tree.addLeft(p, null); // add new sentinel leaves as children
		tree.addRight(p, null);
	} 
	// Some notational shorthands for brevity (yet not efficiency)
	protected Position<Entry<K,V>> root() { return tree.root(); }
	protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p) { return tree.parent(p); }
	protected Position<Entry<K,V>> left(Position<Entry<K,V>> p) { return tree.left(p); }
	protected Position<Entry<K,V>> right(Position<Entry<K,V>> p) { return tree.right(p); }
	protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p) { return tree.sibling(p); }
	protected boolean isRoot(Position<Entry<K,V>> p) { return tree.isRoot(p); }
	protected boolean isExternal(Position<Entry<K,V>> p) { return tree.isExternal(p); }
	protected boolean isInternal(Position<Entry<K,V>> p) { return tree.isInternal(p); }
	protected void set(Position<Entry<K,V>> p, Entry<K,V> e) { tree.set(p, e); }
	protected Entry<K,V> remove(Position<Entry<K,V>> p) { return tree.remove(p); }

	 
	 /** Returns the position in p's subtree having given key (or else the terminal leaf).*/
	private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key) 
	{  
		if (isExternal(p))
			return p; // key not found; return the final leaf
		int comp = compare(key, p.getElement( ));
		if (comp == 0)
			return p; // key found; return its position
		else if (comp < 0)
			return treeSearch(left(p), key); // search left subtree
		else
			return treeSearch(right(p), key); // search right subtree
	}
	/** Returns the value associated with the specified key (or else null). */
	public V get(K key) throws IllegalArgumentException 
	{  
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root( ), key);
		//rebalanceAccess(p); // hook for balanced tree subclasses
		if (isExternal(p)) return null; // unsuccessful search
		return p.getElement( ).getValue( ); // match found
	} 
	/** Associates the given value with the given key, returning any overridden value.*/
	public V put(K key, V value) throws IllegalArgumentException 
	{  
		checkKey(key); // may throw IllegalArgumentException
		Entry<K,V> newEntry = new MapEntry<>(key, value);
		Position<Entry<K,V>> p = treeSearch(root( ), key);
		if (isExternal(p)) { // key is new
			expandExternal(p, newEntry);
			//rebalanceInsert(p); // hook for balanced tree subclasses
			return null;
		} 
		else 
		{ 
			// replacing existing key
			V old = p.getElement( ).getValue();
			set(p, newEntry);
			//rebalanceAccess(p); // hook for balanced tree subclasses
			return old;
		}  
	} 
	/** Removes the entry having key k (if any) and returns its associated value. */
	public V remove(K key) throws IllegalArgumentException 
	{  
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root( ), key);
		if (isExternal(p)) { // key not found
			//rebalanceAccess(p); // hook for balanced tree subclasses
			return null;
		} 
		else 
		{  
			V old = p.getElement( ).getValue( );
			if (isInternal(left(p)) && isInternal(right(p))) 
			{ 
				// both children are internal
				Position<Entry<K,V>> replacement = treeMax(left(p));
				set(p, replacement.getElement( ));
				p = replacement;
			} 
			// now p has at most one child that is an internal node
			Position<Entry<K,V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
			Position<Entry<K,V>> sib = sibling(leaf);
			remove(leaf);
			remove(p); // sib is promoted in p’s place
			//rebalanceDelete(sib); // hook for balanced tree subclasses
			return old;
		}  
	}
	/**
	 * Returns position with the minimal key in the subtree rooted at Position p.
	 * @param p  a Position of the tree serving as root of a subtree
	 * @return Position with minimal key in subtree
	 */
	protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) 
	{
		Position<Entry<K,V>> walk = p;
		while (isInternal(walk))
			walk = left(walk);
		return parent(walk);              // we want the parent of the leaf
	}
	/** Returns the position with the maximum key in subtree rooted at Position p. */
	protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) 
	{  
		Position<Entry<K,V>> walk = p;
		while (isInternal(walk))
			walk = right(walk);
		return parent(walk); // we want the parent of the leaf
	} 
	/**
    * Returns the entry having the least key (or null if map is empty).
    * @return entry with least key (or null if map is empty)
    */
	@Override
	public Entry<K,V> firstEntry() {
		if (isEmpty()) return null;
		return treeMin(root()).getElement();
	}
   
	/** Returns the entry having the greatest key (or null if map is empty). */
	public Entry<K,V> lastEntry( ) 
	{  
		if (isEmpty( )) return null;
		return treeMax(root( )).getElement( );
	}
	/**
    * Returns the entry with least key greater than or equal to given key
    * (or null if no such key exists).
    * @return entry with least key greater than or equal to given (or null if no such entry)
    * @throws IllegalArgumentException if the key is not compatible with the map
    */
	@Override
	public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException
	{
		checkKey(key);                              // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p)) return p.getElement();   // exact match
		while (!isRoot(p)) {
			if (p == left(parent(p)))
				return parent(p).getElement();          // parent has next greater key
			else
				p = parent(p);
		}
		return null;                                // no such ceiling exists
	}
	/** Returns the entry with greatest key less than or equal to given key (if any). */
	public Entry<K,V> floorEntry(K key) throws IllegalArgumentException 
	{  
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root( ), key);
		if (isInternal(p)) return p.getElement( ); // exact match
		while (!isRoot(p)) 
		{ 
			if (p == right(parent(p)))
				return parent(p).getElement( ); // parent has next lesser key
			else
				p = parent(p);

		} 
		return null; // no such floor exists
   	} 
	/** Returns the entry with greatest key strictly less than given key (if any). */
	public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException 
	{  
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root( ), key);
		if (isInternal(p) && isInternal(left(p)))
			return treeMax(left(p)).getElement( ); // this is the predecessor to p
		// otherwise, we had failed search, or match with no left child
		while (!isRoot(p)) 
		{ 
			if (p == right(parent(p)))
				return parent(p).getElement( ); // parent has next lesser key
			else
				p = parent(p);
		}  
		return null; // no such lesser key exists
	}
	/**
    * Returns the entry with least key strictly greater than given key
    * (or null if no such key exists).
    * @return entry with least key strictly greater than given (or null if no such entry)
    * @throws IllegalArgumentException if the key is not compatible with the map
    */
	@Override
	public Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
		checkKey(key);                               // may throw IllegalArgumentException
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p) && isInternal(right(p)))
			return treeMin(right(p)).getElement();     // this is the successor to p
		// otherwise, we had failed search, or match with no right child
		while (!isRoot(p)) {
			if (p == left(parent(p)))
				return parent(p).getElement();           // parent has next lesser key
			else
				p = parent(p);
		}
		return null;                                 // no such greater key exists
   }
   
   /** Returns an iterable collection of all key-value entries of the map. */
   public Iterable<Entry<K,V>> entrySet( ) 
   {  
	   ArrayList<Entry<K,V>> buffer = new ArrayList<>(size( ));
	    
	   for (Position<Entry<K,V>> p : tree.inorder( ))
		   if (isInternal(p)) buffer.add(p.getElement( ));
	   return buffer;
    } 
    /** Returns an iterable of entries with keys in range [fromKey, toKey). */
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) 
    {  
    	ArrayList<Entry<K,V>> buffer = new ArrayList<>(size( ));
    	if (compare(fromKey, toKey) < 0) // ensure that fromKey < toKey
    		subMapRecurse(fromKey, toKey, root( ), buffer);
	    return buffer;
    }
    //
    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p,
    ArrayList<Entry<K,V>> buffer) 
    {  
    	if (isInternal(p))
    		if (compare(p.getElement( ), fromKey) < 0)
    			// p's key is less than fromKey, so any relevant entries are to the right
			    subMapRecurse(fromKey, toKey, right(p), buffer);
		    else 
		    {  
		    	subMapRecurse(fromKey, toKey, left(p), buffer); // first consider left subtree
		    
		    	if (compare(p.getElement( ), toKey) < 0) 
		    	{ // p is within range
		    		buffer.add(p.getElement( )); // so add it to buffer, and consider
		    		subMapRecurse(fromKey, toKey, right(p), buffer); // right subtree as well
		    	}  
		    }  
   	}
    //
    public static void main (String[] args)
    {
    	//create a Binary Search Tree and make a search
  	  	TreeMap<Integer,String> map = new TreeMap<Integer,String> ();
  	  	map.put(6, "A");
  	  	map.put(2, "B");
  	  	map.put(4, "C");
  	  	map.put(1, "D");
  	  	map.put(9, "E");
  	  	map.put(8, "F");
  	  	//search
  	  	System.out.println(map.get(4));	  
  	  	System.out.println(map.higherEntry(2));
  	  	//print the values
  	  	for(String value : map.values()) 
  	  	{
  	  		System.out.println(value);	  
  	  	}
  	  	//print the root element
  	  	System.out.println("root: "+map.root().getElement());	  
  	  	//print both keys and values
  	  	System.out.println(map.entrySet());	  
  	  	//prints elements in increasing order by keys
  	  	for (Position<Entry<Integer,String>> p : map.tree.inorder())
  	  		System.out.println(p.getElement());
  	  	map.dump();
  	  
  	  	//remove the tree node with key 1
  	  	map.remove(1);
  	  	map.dump();
  	  	//insert (4, "COMP")
  	  	//insert (11, "SET")
  	  	//print it again
  	  	map.put(4, "COMP");
  	  	map.put(11, "SET");
  	  	map.dump();
  	  	//get element with key 11
  	  	System.out.println(map.get(11));
  	  	map.dump();
  	  	//create the binary search tree from  slide 15 and perform
  	  	//remove 4, insert 4, insert 5, remove 4 
  	  	//create the binary search tree from slide 22 and perform
  	  	//remove 3 operation
  	  
    }
}
