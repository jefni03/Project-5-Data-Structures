package ADTPackage.Dictionary;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
   A class that implements the ADT dictionary by using a chain of linked nodes.
   The dictionary is sorted and has distinct search keys.
   Search keys and associated values are not null.
  
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class LinkedDictionary<K extends Comparable<? super K>, V> 
             implements DictionaryInterface<K, V>
{
	private Node firstNode; // Reference to first node of chain
	private int  numberOfEntries; 
	
	public LinkedDictionary()
	{
      initializeDataFields();
	} // end default constructor
	
	public void initializeDataFields(){
		firstNode = null;
		numberOfEntries = 0;
	}

   public V add(K key, V value)
	{
		V result = null;
      if ((key == null) || (value == null))
         throw new IllegalArgumentException("Cannot add null to a dictionary.");
      else
      {
         // Search chain until you either find a node containing key
         // or locate where it should be
         Node currentNode = firstNode;
         Node nodeBefore = null;
         
         while ( (currentNode != null) && (key.compareTo(currentNode.getKey()) > 0) )
         {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
         } // end while
         
         if ( (currentNode != null) && key.equals(currentNode.getKey()) )
         {
            // Key in dictionary; replace corresponding value
            result = currentNode.getValue(); // Get old value
            currentNode.setValue(value);     // Replace value
         }
         else // Key not in dictionary; add new node in proper order
         {
            // Assertion: key and value are not null
            Node newNode = new Node(key, value); // Create new node
            
            if (nodeBefore == null)
            {                                    // Add at beginning (includes empty chain)
               newNode.setNextNode(firstNode);
               firstNode = newNode;
            }
            else                                 // Add elsewhere in non-empty chain
            {
               newNode.setNextNode(currentNode); // currentNode is after new node
               nodeBefore.setNextNode(newNode);  // nodeBefore is before new node
            } // end if

            numberOfEntries++;                   // Increase length for both cases
         } // end if
      } // end if

		return result;
	} // end add

	@Override
	public V remove(K key)
		{
			V result = null;
		  if ((key == null))
			 throw new IllegalArgumentException("Cannot find a null key.");
		  else
		  {
			 // Search chain until you either find a node containing key
			 // or locate where it should be
			 Node currentNode = firstNode;
			 Node nodeBefore = null;
			 
			 while ( (currentNode != null) && (key.compareTo(currentNode.getKey()) > 0) )
			 {
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			 } // end while
			 
			 if ( (currentNode != null) && key.equals(currentNode.getKey()) )
			 {
				// Key in dictionary; replace corresponding value
				result = currentNode.getValue(); // Get old value
				currentNode.setValue(value);     // Replace value
			 }
			 else // Key not in dictionary; add new node in proper order
			 {
				// Assertion: key and value are not null
				Node newNode = new Node(key, value); // Create new node
				
				if (nodeBefore == null)
				{                                    // Add at beginning (includes empty chain)
				   newNode.setNextNode(firstNode);
				   firstNode = newNode;
				}
				else                                 // Add elsewhere in non-empty chain
				{
				   newNode.setNextNode(currentNode); // currentNode is after new node
				   nodeBefore.setNextNode(newNode);  // nodeBefore is before new node
				} // end if
	
				numberOfEntries++;                   // Increase length for both cases
			 } // end if
		  } // end if
	
			return result;
		} // end add
		
	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key)
    {
        boolean contains = false;
        Node<K, V> currentNode = firstNode;
        //Node<K, V> nodeBefore = null;
        while ((currentNode != null))
        {
            if (key.equals(currentNode.getKey()))
            {
                contains = true;
                break;
            }
            //nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        } // end while
        return contains;
	{}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
   
	private class Node<K, V>
	{
		private K key;
		private V value;
		private Node<K,V> next;
		private Node(K keyPortion, V dataPortion)
        {
            key = keyPortion;
            value = dataPortion;
            next = null;
        } // end constructor

        private Node(K keyPortion, V dataPortion, Node<K, V> nextNode)
        {
            key = keyPortion;
            value = dataPortion;
            next = nextNode;
        } // end constructor

        private Node<K, V> getNextNode()
        {
            return next;
        } // end getNextNode

        private K getKey()
        {
            return key;
        }

        private V getValue()
        {
            return value;
        }

		private void setValue(V value){
			this.value = value;
		}

        private void setNextNode(Node<K, V> nextNode)
        {
            next = nextNode;
        } // end setNextNode 

	} // end Node


	private class KeyIterator implements Iterator<K>
    {
        private Node<K, V> nextNode;

        private KeyIterator()
        {
            nextNode = firstNode;
        }

        public boolean hasNext()
        {
            return nextNode != null;
        }

        public K next()
        {
            K result;
            if (hasNext())
            {
                result = (K) nextNode.getKey();
                nextNode = nextNode.getNextNode();
            } else
            {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list");
            }
            return result;
        }
    } // end KeyIterator


	private class ValueIterator implements Iterator<V>
    {
        private Node<K, V> nextNode;

        private ValueIterator()
        {
            nextNode = firstNode;
        }

        public boolean hasNext()
        {
            return nextNode != null;
        }

        public V next()
        {
            V result;
            if (hasNext())
            {
                result = (V) nextNode.getValue();
                nextNode = nextNode.getNextNode();
            } else
            {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list");
            }
            return result;
        }
    } // end KeyIterator

} // end SortedLinkedDictionary
		
