package oop.utils.collections;

import java.util.Objects;

import oop.collections.ICollection;
import oop.collections.IList;

public class LinkedList implements IList {

	    private class Node {
	        Object value;
	        Node next;
	        
	        Node(Object data) {
	            this.value = data;
	            this.next = null;}}
	        
	    private Node head;
	    private Node tail;
	    private int size;
	    private int nb_listeners=0;
	    private IList.Listener[] listeners = new IList.Listener[4];
	 	 /**
	 * Constructs an empty list.
	 */
	 public LinkedList() {
		 this.head=null; 
		 this.tail=null; 
		 this.size=0;
	}
	 /**
	 * Constructs a list, initialized with
	 * the elements from the given array.
	 */
	 public LinkedList(Object array[]) {
		 if (array.length==0) {
			 this.size=0; 
			 head=null;
			 return; 
		 }
		 this.head=new Node(array[0]);
		 Node tmp =head; 
		 this.size=1; 
		 for (int i=1; i<array.length; i++) {
			 tmp.next = new Node(array[i]); 
			 tmp = tmp.next; 
			 this.size++; 
		 }
		 this.tail=tmp; 
	}

	 /**
	 * Constructs a list, initialized with
	 * the elements from the given list.
	 */
	 public LinkedList(LinkedList v) {
		 this.head=new Node (v.head.value); 
		 this.tail=this.head; 
		 this.size=1; 
		 Node current  = v.head.next; //psq on veut commencer le parcours a partir du 2eme avec une boucle while
		 while (current!=null) {
			 Node tmp= new Node(current.value); 
			 this.tail.next=tmp; 
			 this.tail=tmp; 
			 current=current.next; 
			 this.size++; }
	}
	 /**
	 * Constructs a list, initialized with
	 * the elements from the given collection.
	 */
	 public LinkedList(ICollection c) {
		 Object[] array= new Object[c.length()]; 
		 c.toArray(array);
		 if (array.length==0) {
			 this.size=0; 
			 head=null;
			 return; 
		 }
		 this.head=new Node(array[0]);
		  tail =head; 
		 this.size=1; 
		 for (int i=1; i<array.length; i++) {
			 tail.next = new Node(array[i]); 
			 tail = tail.next; 
			 this.size++; 
		 }
	}
	 
	 
	
	@Override
	public int length() {
		return this.size; 
	}

    private static class Iterator implements ICollection.Iterator {

        private Node current;     
        private Node prec;   
        private LinkedList list;  

        public Iterator(LinkedList list) {
            this.list = list;
            this.current = list.head;
            this.prec = null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new IllegalStateException();
            Object val = current.value;
            prec = current;
            current = current.next;
            return val;
        }

        @Override
        public Object remove() {
            if (prec == null)
                throw new IllegalStateException("remove sans next");

            Object removed = prec.value;
            list.remove(removed);
            prec = null;
            return removed;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator(this);
    }
	/**
	 * @param index
	 * @return the element at the given index, if the index is valid.
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object elementAt(int index) {
		if ((index < 0) || (index >= this.size)) {
	
			throw new IndexOutOfBoundsException("index out of bounds pour elementAT");}
		else {
			int i=0; 
			Node current = this.head; 
			while (i<index) {
				current=current.next; 
				i++; 
			}
			return current.value; 
		}
	}

	/**
	 * Updates the element at the given index, if the index is valid.
	 * 
	 * @param index
	 * @param niu
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object updateAt(int index, Object niu) {
		if ((index < 0) || (index >= this.size)) {
			
			throw new IndexOutOfBoundsException("index out of bounds pour updateAt");}
		else {
			int i=0; 
			Node current = this.head; 
			while (i<index) {
				current=current.next; 
				i++; 
			}
			
			Object old = current.value;
			current.value = niu;
			for (int j = 0; j < nb_listeners; j++) {
			    listeners[j].updated(this, index, old, niu);
			}
			return old;

		}
	}
	/**
	 * Insert the element at the given index. Nota Bene: if the index is greater
	 * than the list length, the list is grown.
	 * 
	 * @param index
	 * @param elem
	 * @throws IndexOutOfBoundsException if the index is negative.
	 */

	
	@Override
	public void insertAt(int index, Object elem) {
	    if (index < 0) {
	    	throw new IndexOutOfBoundsException("index negatif ;("); 
	    }
	   
	    if (index == 0) {
	        Node newNode = new Node(elem);
	        newNode.next = head;
	        head = newNode;
	        if (size == 0) tail = newNode;
	        size++;
	    }
	    else if (index > size) {
	        Node current = tail;
	        if (current == null) { // liste vide
	            head = tail = new Node(null);
	            current = head;
	            size = 1;
	        }

	        while (size < index) {
	            current.next = new Node(null);
	            current = current.next;
	            size++;
	        }

	        current.next = new Node(elem);
	        tail = current.next;
	        size++;
	    }

	    else if (index == size) {
	        Node newNode = new Node(elem);
	        tail.next = newNode;
	        tail = newNode;
	        size++;
	    }

	    else {
	        Node current = head;
	        for (int i = 0; i < index - 1; i++)
	            current = current.next;

	        Node newNode = new Node(elem);
	        newNode.next = current.next;
	        current.next = newNode;
	        size++;
	    }
	    for (int i = 0; i < nb_listeners; i++)
	        listeners[i].inserted(this, index, elem);
	}





	/**
	 * Removes the element at the given index, if the index is valid.
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException otherwise.
	 */
	public Object removeAt(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		Object removed; 
		if (index == 0) {
	        removed= head.value;
	        head = head.next;
	        if (head == null) {
	            tail = null;}}
	  else {
		Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;}
         removed = current.next.value;
        current.next = current.next.next;
        if (current.next == null) {
            tail = current;}}
        size--;
        
		 for (int i = 0; i < nb_listeners; i++) {
		        listeners[i].removed(this, index, removed);
		    }
		return removed;
	}

	/**
	 * Remove the element if found, otherwise does nothing
	 * 
	 * @param index_courant
	 * @return true if the element was found and removed
	 */
	  @Override
	  public boolean remove(Object elem) {
		  Node current = this.head;
	      for (int j = 0; j < size; j++) {
	          if (Objects.equals(current.value, elem)) {
	              removeAt(j); 
	              return true;}
	           current = current.next; }
	      return false;
	  }


	/**
	 * @param elem
	 * @return true if the given element is in the list, using object identity to
	 *         compare objects
	 */
	public boolean contains(Object elem) {
		Iterator iter = iterator();
		while (iter.hasNext()) {
			if (iter.next() == (elem)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Copies the list elements to the given array
	 * 
	 * @param elems
	 */
	public void toArray(Object elems[]) {
			  if (elems==null || elems.length< size) {
		            throw new IllegalArgumentException("tableau fourni nul ou trop petit!!!");}
			  Node current = this.head;
		      for (int j = 0; j < size; j++) {
		          elems[j]=current.value; 
		    	  current=current.next; 
		  }}
		  


	@Override
	public void add(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return;  }
		if (l instanceof IList.Listener) {
		    add((IList.Listener) l);
		}

		else {
			throw new IllegalArgumentException("add du listener collcetion echoué");
		}

	}

	@Override
	public void drop(oop.collections.ICollection.Listener l) {
		if (l == null) {
			return;  }
		if (l instanceof oop.collections.IList.Listener) {

			drop((IList.Listener) l);
		} else {
			throw new IllegalArgumentException("drop du listener collcetion echoué");
		}
	}

	@Override
    public void add(IList.Listener l) {
        if (l == null) return;
        for (int i = 0; i < nb_listeners; i++)
            if (listeners[i].equals(l)) return;
        if (nb_listeners == listeners.length) { //agrandir tableau
            IList.Listener[] tmp = new IList.Listener[listeners.length + 4];
            System.arraycopy(listeners, 0, tmp, 0, nb_listeners);
            listeners = tmp;
        }
        listeners[nb_listeners++] = l;
        l.added(this);
    }

    @Override
    public void drop(IList.Listener l) {
        if (l == null) return;
        for (int i = 0; i < nb_listeners; i++) {
            if (listeners[i].equals(l)) {
                for (int j = i; j < nb_listeners - 1; j++) listeners[j] = listeners[j + 1];
                listeners[--nb_listeners] = null;
                l.dropped(this);
                return;
            }
        }
    }

	}