import java.util.Iterator;

/**
 * Linked list implementation of the MyList interface.
 * @author Stephen Connelly Sjc2235
 * @version 1.0 October 6th, 2022
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node head, tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index    index of the element to return
     * @param element  element to be stored at the specified position
     * @return  the element at the specified position in this list
     * @throws  IndexOutOfBoundsException - if the index is out of range
     *          (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        E oldElement = p.element;
        p.element = element;
        return oldElement;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of the element to return
     * @return       the element at the specified position in this list
     * @throws       IndexOutOfBoundsException - if the index is out of range
     *               (index < 0 || index >= size())
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        return p.element;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element  element to be appended to this list
     * @return true
     */
    public boolean add(E element) {
        Node n = new Node(element);
        if (head == null) {
            head = tail = n;
        }else{
            tail.next = n;
            tail = n;
        }
        size++;
        return true;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index    index at which the specified element is to be inserted
     * @param element  element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index > size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node n = new Node(element);
        Node p = head;
        if (head == null) { //if list is empty
            head = tail = n; //set both tail and head as the node
        }
            if (index == 0) {
                n.next = head;
                head = n;
                size++;
            }else {
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                n.next = p.next;
                p.next = n;
                size++;
            }
        }

    /**
     * Removes the element at the specified position in this list.
     * @param index  the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node curr = head;
        Node prev = null;
        Node temp;
           if(index == 0){
               E returned = head.element;
               head = head.next;
               size--;
               return returned;

           }
           for (int i = 0; i < index; i++){
                temp = curr;
                curr = curr.next;
                prev = temp; //j = index
           }
           prev.next = curr.next;
           E returned = prev.element;
            size--;
           return returned;
        }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More
     * formally, returns the lowest index i such that Objects.equals(o, get(i)),
     * or -1 if there is no such index.
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in
    Lists - 2
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(E element) {
        Node p = head;
        Node n = new Node(element);
        for(int i = 0; i<size; i++){
            if(n.element == p.element){
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    /**
     * Returns an array of indexes of each occurrence of the specified element
     * in this list, in ascending order. If the specified element is not found,
     * a non-null empty array (not null) is returned.
     * @param element element to search for
     * @return an array of each occurrence of the specified element in this
     * list
     */
    public int[] indexesOf(E element) {
        Node p = head;
        Node h = head;
        Node n = new Node(element);
        int frequency = 0;
        for(int i = 0; i < size; i++){
            if(p.element == n.element){
                frequency++;
            }
            p = p.next;
        }
        int j = 0;
        int[] newData = new int[frequency];
        for(int i = 0; i<size; i++){
            if(h.element == n.element){
                newData[j] = i;
                j++;
            }
            h = h.next;
        }
        return newData;
    }
    /**
     * Reverses the data in the list.
     * For MyArrayList, the data inside the underlying is moved. For
     * MyLinkedList, the tail must become the head, and all the pointers are
     * reversed. Both implementations must run in Theta(n).
     */
    public void reverse() {
        Node p = head;
        if(size == 2){
            head = tail.next;
            tail = head;
        }else {
            for (int i = 0; i < size - 2 - i; i++) {
                head = tail.next;
                p = tail;
                p.next = null;
            }
        }
    }
    /**
     * Returns a string representation of the list. The string will begin with
     * a '[' and end with a ']'. Inside the square brackets will be a comma-
     * separated list of values, such as [Brian, Susan, Jamie].
     * @return a string representation of the list.
     */
    public String toString(){
        String emptyString = "";
        String start = "[";
        String end ="]";
        Node p = head;
        if(size==1){
            emptyString += p.element;
            return start + emptyString + end;
        }
        for(int i =0; i<size; i++) {
            emptyString += p.element;
            p = p.next;
            if (i != size - 1) {
                emptyString += ", ";
            }
        }
        String returnString = start + emptyString + end;
        return returnString;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator() {
        return new ListItr();
    }

    private class ListItr implements Iterator<E> {
        private Node current;

        ListItr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Node next;
        E element;
        public Node(E element) {
            this.element = element;
        }
    }



}
