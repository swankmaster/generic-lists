package cs1302;
import cs1302.Iterator;
import cs1302.Node;
import cs1302.genlistadt.*;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

public class LinkedGenList<T> implements GenList<T>, Iterable<T> {

    private int size;
    private Node<T> gen;
    

    /**
     *{@code LinkedGenList} is the default constructor.
     *It sets size to 0 and the Node to null.
     */

    public LinkedGenList() {
        this.size = 0;
        this.gen = null;
    }

    /**
     *{@code LinkedGenList} is the copy constructor.
     *It imports the list into the instance Node gen.
     *@param <U> is the type of the list.
     *@param other is the list being created within the constructor
     */


    public <U extends T> LinkedGenList(GenList<U> other) {
        LinkedGenList<T> u = (LinkedGenList<T>) other;
        int us = other.size();
        this.gen = null;
        for (int i = 0; i < us; i++) {
            this.add(u.get(i));
        }

    }

    /**
     *{@code add} adds the list at the specified index.
     *@param index represents where the list will be placed.
     *@param list is the list being added.
     *@param <U> is the type of GenList.
     *@return a boolean value.
     */

    public <U extends T> boolean add(int index, GenList<U> list) {
        if (list == null) {
            throw new java.lang.NullPointerException("List is null");
        } else if (index < 0 || index > size()) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } //else-if

        LinkedGenList<T> l = (LinkedGenList<T>) list; // handles type mismatch
        Node<T> temp = this.gen;
        int count = 0;
        while (count < index - 1) {
            temp = temp.getNext();
            count++;
        } // while

        Node<T> head = l.first();
        while (head.getNext() != null) {
            head = head.getNext();
        } // while
        if (index == 0) {
            head.setNext(temp);
            this.gen = l.first();
        } else {
            if (index != this.size) {
                head.setNext(temp.getNext());
            } // if
            temp.setNext(l.first());
        } // if-else
        this.size = this.size + l.size();
        return true;
    }

    /**
     *{@code add} iterates through each element in the list and adds it to the end of the current
     *list.
     *@param list represents the list that is added.
     *@param <U> is the type of list.
     *@return a boolean value.
     */

    public <U extends T> boolean add(GenList<U> list) {
        this.add(this.size, list);
        return true;
    }

    /**
     *{@code first} returns the points the instance Node to the first one.
     *@return the first Node in the list.
     *
     */

    public Node<T> first() {
        return this.gen;
    }

    /**
     *{@code add} method adds an object at the specified index of the list.
     *@param index specifies the index where the object is being placed.
     *@param obj represents the object being added.
     *@return a boolean value.
     */
    public boolean add(int index, T obj) {

        if (obj == null) {
            throw new java.lang.NullPointerException("Object is null");
        } else if (index < 0 || index > size()) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } // else if
        Node<T> input = new Node<>(obj);
        Node<T> temp = this.gen;
        int count = 0;
        while (count < index - 1) {
            temp = temp.getNext();
            count++;
        } // while
        if (index == 0) {
            input.setNext(this.gen);
            this.gen = input;
        } else {
            if (index != this.size) { //issue
                input.setNext(temp.getNext());
            } // if
            temp.setNext(input);
        } // if-else
        this.size++;
        return true;
    }

    /**
     *{@code add} method adds an object to the end of the list.
     *@param obj represents the object being added.
     *@return a boolean value.
     */

    public boolean add(T obj) {
        this.add(this.size, obj);
        return true;
    }

    /**
     *{@code anyMatch} method sees if all elements match the criteria specified by the predicate.
     *@param p is the predicate that is the basis of the search.
     *@return a boolean value that returns true if there are any elements that match the criteria.
     */

    public boolean allMatch(Predicate<T> p) {
        if (p == null) {
            throw new java.lang.NullPointerException("Predicate is null");
        } // if

        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            if (!p.test(i.next())) {
                return false;
            } // if
        } // while
        return true;
    }

    /**
     *{@code anyMatch} method sees if any elements match the criteria specified by the predicate.
     *@param p is the predicate that is the basis of the search.
     *@return a boolean value that returns true if there are any elements that match the criteria.
     */

    public boolean anyMatch(Predicate<T> p) {

        if (p == null) {
            throw new java.lang.NullPointerException("Predicate is null");
        } // if

        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            if (p.test(i.next())) {
                return true;
            } // if
        } // while
        return false;
    }

    /**
     *{@code iterator} method iterates through the list.
     *@return an Iterator object that can move through the list.
     */

    public Iterator<T> iterator() {
        return new Iterator<T> (this);
    }

    /**
     *{@code filter} method filters the list based on the parameter.
     *@param p represents a predicate that is used to filter with.
     *@return a GenList object that contains all elements that pass the test.
     */

    public GenList<T> filter (Predicate<T> p) {
        if (p == null) {
            throw new java.lang.NullPointerException("Predicate is null");
        } // if
        T obj;
        GenList <T> filt = new LinkedGenList<> ();
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            obj = i.next();
            if (p.test(obj)) {
                filt.add(obj);
            } // if
        } // while
        return filt;
    }

    /**
     *{@code size} method is used to show the size.
     *@return an integer object that represents the size of the lists internal storage.
     */

    public int size() {
        return this.size;
    }

    /**
     *{@code map} method maps elements in the list.
     *@param f represents the function used to map elements
     *@param <R> is the type of the function.
     *@return a list object that have been mapped.
     */

    public <R>GenList<R> map(Function<T,R> f) {
        if (f == null) {
            throw new java.lang.NullPointerException("Object is null");
        } // if
        LinkedGenList<R> map = new LinkedGenList<> ();
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            R apply = f.apply(i.next());
            if (apply == null) {
                throw new java.lang.NullPointerException("Object is null");
            } // if
            map.add(apply);
        } // while
        return map;
    }

    /**
     *{@code isEmpty} checks if the list is empty.
     *@return a boolean value that represents true if it is empty.
     */

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        } // if-else
    }

    /**
     *{@code max} method finds the largest element in the list.
     *@param c is the comparator object that is used.
     *@return the largest object in the list.
     */

    public T max(Comparator<T> c) {

        if (c == null) {
            throw new java.lang.NullPointerException("Comparator is null");
        } // if
        int val;
        T comp;
        T large = this.gen.getVal();
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            comp = i.next();
            val = c.compare(large, comp);
            if (val < 0) {
                large = comp;
            } // if
        } // while
        return large;
    }

    /**
     *{@code min} method returns the smallest object in the list.
     *@param c is the comparator object that is used to gain insight.
     *@return the smallest object.
     */

    public T min(Comparator<T> c) {
        if (c == null) {
            throw new java.lang.NullPointerException("Comparator is null");
        } // if
        int val;
        T comp;
        T small = this.gen.getVal();
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            comp = i.next();
            val = c.compare(small, comp);
            if (val > 0) {
                small = comp;
            } // if
        } // while
        return small;
    }

    /**
     *{@code reduce} method reduces the list.
     *@param start specifies what is changed about the list.
     *@param f specfies how the list is changed.
     *@return the final object created.
     */

    public T reduce(T start, BinaryOperator<T> f) {
        if (f == null) {
            throw new java.lang.NullPointerException("Function is null");
        }
        T temp = start;
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            temp = f.apply(temp, i.next());
            if (temp == null) {
                throw new java.lang.NullPointerException("Result of apply is null");
            } // if
        } // while

        return temp;
    }

    /**
     *{@code toArray} method creates an array of objects from the list.
     *@param other passes in a list.
     *@return an array.
     */

    public T[] toArray(IntFunction<T[]> other) {

        T[] a = other.apply(this.size());
        Iterator<T> n = this.iterator();
        T temp;
        for (int i = 0;i < a.length; i++) {
            temp = n.next();
            a[i] = temp;
        }
        return a;
    }

    /**
     *{@code clear} method clears the list and resets size to 0.
     *
     */

    public void clear() {
        this.gen = new Node<T>();
        this.size = 0;
    }

    /**
     *{@code get} method retrieves an element.
     *@param index specifies where the object is.
     *@return the object that is at the index.
     */

    public T get(int index) {

        if (index < 0 || index >= size()) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } // if
        int count = 0;
        Node<T> temp = this.gen;
        while (count < index) {
            temp = temp.getNext();
            count++;
        } // while
        if (temp == null) {
            return null;
        } // if
        return temp.getVal();
    }

    /**
     *{@code remove} removes an object from the list.
     *@param index specfies where the object is.
     *@return the removed object
     */

    public T remove(int index) {

        if (index < 0 || index >= size()) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } // if
        T rm;
        Node<T> temp = this.gen;
        for (int d = 0; d < index - 1; d++) {
            temp = temp.getNext();
        } // for
        if (index != 0) {
            rm = temp.getNext().getVal();
            temp.setNext(temp.getNext().getNext());
            return rm;
        } else {
            rm = temp.getVal();
            this.gen = this.gen.getNext();
            return rm;
        } // if-else
    }

    /**
     *{@code set} method replaces an object in the list.
     *@param index represents where the new object will be placed.
     *@param obj represents the object being placed
     *@return the object that was removed
     */

    public T set(int index, T obj) {

        if (obj == null) {
            throw new java.lang.RuntimeException("Invalid object");
        } // if

        if (index < 0 || index >= size()) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } // if

        T rm = this.get(index);
        this.remove(index);
        this.add(index, obj);
        return rm;
    }

    /**
     *{@code indexOf} method finds an object in a list.
     *@param obj specifies the object that it is looking for.
     *@return the index of the specified object.
     */

    public int indexOf(T obj) {

        if (obj == null) {
            throw new java.lang.NullPointerException("Object is null");
        } // if

        int index;
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(this.get(i))) {
                index = i;
                return index;
            } // if
        } // for

        return -1;
    }

    /**
     *{@code reverse} method reverses the order of elements in the list.
     *@return a list object with elements reversed.
     */

    public GenList<T> reverse() {

        GenList<T> rev = new LinkedGenList<T>();
        for (int i = this.size - 1; i >= 0; i--) {
            rev.add(this.get(i));
        } // for
        return rev;
    }

    /**
     *{@code distinct} method reveals unique elements in the list.
     *@return a list object with the valid elements.
     */

    public GenList<T> distinct() {

        GenList<T> distinct = new LinkedGenList<>();
        boolean isDistinct;
        T elem;
        for (int r = 0; r < this.size - 1; r++) {
            elem = this.get(r);
            isDistinct = true;
            for (int c = 0; c < this.size; c++) {
                if (r == c) {
                    continue;
                } else if (elem.equals(this.get(c))) {
                    isDistinct = false;
                    break;
                } // else-if
            } // for
            if (isDistinct) {
                distinct.add(elem);
            } // if
        } // for
        return distinct;
    }

    /**
     *{@code splice} method chooses objects from parameters.
     *@param fromIndex specifies the start point (inclusive).
     *@param toIndex specifies the end point (exclusive).
     *@return a {@code GenList} list.
     */

    public GenList<T> splice(int fromIndex, int toIndex) {

        if (fromIndex < 0 || toIndex > this.size || fromIndex > toIndex) {
            throw new java.lang.IndexOutOfBoundsException("Invalid index");
        } // if
        GenList<T> splice = new LinkedGenList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            splice.add(this.get(i));
        } // for
        return splice;
    }

    /**
     *{@code contains} method sees if the object is contained in the list.
     *@param o is of type T.
     *@return a boolean value.
     */

    public boolean contains(T o) {

        if (o == null) {
            throw new java.lang.NullPointerException("Object is null");
        }
        Iterator<T> i = this.iterator();
        while (i.hasNext()) {
            if (o.equals(i.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     *{@code makeString} method makes a string of objects in the list.
     *@param sep is the separator between objects.
     *@return result String object.
     */

    public String makeString(String sep) {
        String result = "";
        int check = this.size - 1;
        Node<T> temp = this.gen;
        for (int g = 0; g < this.size; g++) {
            if (g < check) {
                result += temp.getVal() + sep;
                temp = temp.getNext();
            } else {
                result += temp.getVal();
            } // if-else
        } // for
        return result;
    }

    /**
     *{@code makeString} method makes a string.
     *@return result String object.
     */

    public String makeString() {
        String result = this.makeString("");
        return result;
    }

    /**
     *{@code makeString} method creates a string.
     *@param start is placed at the beginning.
     *@param sep is the separator.
     *@param end is put at the end.
     *@return result String.
     */

    public String makeString(String start, String sep, String end) {
        String result = start + this.makeString(sep) + end;
        return result;
    }

//    public LinkedGenList() {
//	this.size = 0;
//	this.gen = null;
//    }
//
//    public <U extends T> LinkedGenList(GenList<U> other) {
//
//	this.size = other.size();
//	this.gen = null;
//	for(int i=0; i<this.size; i++) {
//		this.add(other.get(i));
//	}
//	
//	
//	
//    }
//
//    public <U extends T> boolean add(int index, GenList<U> list) {
//
//	if(list == null){
//            throw new java.lang.NullPointerException("List is null");
//        } //if
//        else if(index < 0 || index > size()){
//            throw new java.lang.IndexOutOfBoundsException("Invalid index");
//        } //else if
//
//	LinkedGenList<T> l = new LinkedGenList<T>(list);
//	Node<T> temp = this.gen;
//	int count = 0;
//	while (count < index - 1) {
//	    temp = temp.getNext();
//	    count++;
//	} // while
//
//	Node<T> head = l.first();
//	while (head.getNext() != null) {
//	    head = head.getNext();
//	} // while
//
//	if (index == 0) {
//	    head.setNext(temp);
//	    this.gen = l.first();
//	} else {
//	    if(index != this.size) {
//		head.setNext(temp.getNext());
//	    }
//	    temp.setNext(l.first());
//	}
//	this.size = l.size();
//	return true;
//
//    }
//
//    public <U extends T> boolean add(GenList<U> list) {
//
//	this.add(this.size, list);
//	return true;
//    }
//
//    public Node<T> first() {
//	return this.gen;
//    }
//
//    public boolean add(int index, T obj) {
//
//	if(obj == null){
//            throw new java.lang.NullPointerException("Object is null");
//        } // if
//        else if(index < 0 || index > size){
//            throw new java.lang.IndexOutOfBoundsException("Invalid index");
//        } // else if
//	Node<T> input = new Node<>(obj);
//	Node<T> temp = this.gen;
//	int count = 0;
//	while (count < index-1) {
//	    temp = temp.getNext();
//	    count++;
//	} // while
//	if (index == 0) {
//	    input.setNext(this.gen);
//	    this.gen = input;
//	} else {
//	    if(index != this.size) {
//		input.setNext(temp.getNext());
//	    } // if
//	    temp.setNext(input);
//	} // if-else
//
//	this.size++;
//	return true;
//    }
//
//    public boolean add(T obj) {
//	this.add(this.size, obj);
//	return true;
//    }
//
//    public boolean allMatch(Predicate<T> p) {
//
//	if(p == null) {
//	    throw new java.lang.NullPointerException("Predicate is null");
//	} // if
//	
//	Iterator<T> i = this.iterator();
//	while (i.hasNext()) {
//	    if(!p.test(i.next())){
//		return false;
//	    } // if
//	} // while
//	return true;
//    }
//
//    public boolean anyMatch(Predicate<T> p) {
//
//	if(p == null) {
//	    throw new java.lang.NullPointerException("Predicate is null");
//	} // if
//	
//	Iterator<T> i = this.iterator();
//        while (i.hasNext()) {
//            if(p.test(i.next())){
//                return true;
//            } // if
//	} // while
//        return false;
//    }
//
//    public Iterator<T> iterator(){
//	return new Iterator<T> (this);
//    }
//
//    public GenList<T> filter (Predicate<T> p) {
//	if(p == null) {
//	    throw new java.lang.NullPointerException("Predicate is null");
//	} // if
//	T obj;
//	GenList <T> filt = new LinkedGenList<>();
//	Iterator<T> i = this.iterator();
//	while(i.hasNext()) {
//		obj=  i.next();
//	    if(p.test(obj)) {
//		filt.add(obj);
//	    } // if
//	} // while
//	return filt;
//    }
//
//    public int size(){
//	
//	return this.size;
//    }
//
//    public <R>GenList<R> map(Function<T,R> f) {
//	if(f == null){
//            throw new java.lang.NullPointerException("Object is null");
//        } // if
//	LinkedGenList<R> map = new LinkedGenList<> ();
//	Iterator<T> i = this.iterator();
//	while (i.hasNext()) {
//	    R apply = f.apply(i.next());
//	    if(apply == null){
//		throw new java.lang.NullPointerException("Object is null");
//	    } // if
//	    
//	    map.add(apply);
//	} // while
//	
//	return map;
//
//    }
//
//    public boolean isEmpty() {
//	if (this.size() == 0) {
//	    return true;
//	} else {
//	    return false;
//	} // if-else
//    }
//
//    public T max(Comparator<T> c) {
//
//	if (c == null) {
//	    throw new java.lang.NullPointerException("Comparator is null");
//	} // if
//	int val;
//	T comp;
//	T large = this.gen.getVal();
//	Iterator<T> i = this.iterator();
//	while (i.hasNext()) {
//	    comp = i.next();
//	    val = c.compare(large, comp);
//	    if (val < 0) {
//		large = comp;
//	    } // if	
//	} // while
//
//	return large;
//    }
//
//    public T min(Comparator<T> c){
//        if (c == null) {
//            throw new java.lang.NullPointerException("Comparator is null");
//        } // if
//        int val;
//        T comp;
//        T small = this.gen.getVal();
//        Iterator<T> i = this.iterator();
//        while (i.hasNext()) {
//            comp = i.next();
//	    val = c.compare(small, comp);
//            if (val > 0) {
//                small = comp;
//            } // if                                                                                                                                  
//	} // while
//        return small;
//    }
//    
//    public T reduce(T start, BinaryOperator<T> f) {
//	if (f == null) {
//	    throw new java.lang.NullPointerException("Function is null");
//	}
//	T temp = start;
//	Iterator<T> i = this.iterator();
//	while(i.hasNext()) {
//	    temp = f.apply(temp, i.next());
//	    if(temp == null) {
//		throw new java.lang.NullPointerException("Result of apply is null");
//	    }
//	}
//
//	return temp;
//    }
//
//    public T[] toArray(IntFunction<T[]> other) {
//
//	T[] a = other.apply(this.size);
//	Iterator<T> n = this.iterator();
//	T temp;
//	for(int i = 0;i < a.length; i++) {
//	    temp = n.next();
//	    a[i] = temp;
//	}
//
//	return a;
//
//    }
//
//    public void clear() {
//
//	this.gen = new Node<T>();
//	this.size = 0;
//    }
//
//    public T get(int index) {
//	if(index < 0 || index >= this.size) {
//	    throw new java.lang.IndexOutOfBoundsException("Invalid index");
//	} // if
//	int count = 0;
//	Node<T> temp = this.gen;
//	while(count < index ) {
//	    temp = temp.getNext();
//	    count++;
//	} // while
//
//	if(temp ==  null) {
//		return null;
//	}
//	return temp.getVal();
//	
//    }
//
//    public T remove(int index) {
//
//        if(index < 0 || index > size()){
//            throw new java.lang.IndexOutOfBoundsException("Invalid index");
//        } // if                                                                                                      
//        T rm;
//        Node<T> temp = this.gen;
//        for(int d = 0; d < index - 1; d++) {
//            temp = temp.getNext();
//        } // for                                                                                                     
//        if(index != 0) {
//            rm = temp.getNext().getVal();
//            temp.setNext(temp.getNext().getNext());
//            return rm;
//        } // if                                                                                                      
//        else {
//            rm = temp.getVal();
//            this.gen = this.gen.getNext();
//            return rm;
//        } // else                                                                                                    
//    }
//
//    public T set(int index, T obj) {
//
//	if(obj == null){
//            throw new java.lang.RuntimeException("Invalid object");
//	} // if	
//                                                                                                                     
//        if(index < 0 || index > this.size){
//            throw new java.lang.IndexOutOfBoundsException("Invalid index");
//        } // if
//
//	T rm = this.get(index);
//	this.remove(index);
//	this.add(index, obj);
//	return rm;
//    }
//
//    public int indexOf(T obj) {
//
//	if(obj == null){
//            throw new java.lang.NullPointerException("Object is null");
//        } // if
//
//	int index;
//	for(int i = 0; i < this.size; i++) {
//	    if(obj.equals(this.get(i))) {
//		index = i;
//		return index;
//	    }
//	}
//
//	return -1;
//
//    }
//
//    public GenList<T> reverse(){
//
//	GenList<T> rev = new LinkedGenList<T>();
//	for(int i = this.size - 1; i >= 0; i--) {
//	    rev.add(this.get(i));
//	}
//	return rev;
//    }
//
//    public GenList<T> distinct(){
//
//	GenList<T> distinct = new LinkedGenList<>();
//	boolean isDistinct;
//	T elem;
//	for(int r = 0; r < this.size - 1; r++) {
//	    elem = this.get(r);
//	    isDistinct = true;
//	    for(int c = 0; c < this.size; c++){
//		if (r == c) {
//		    continue;
//		}
//		else if(elem.equals(this.get(c))){
//		    isDistinct = false;
//		    break;
//		} // if
//	    } // for
//	    if(isDistinct){
//		distinct.add(elem);
//	    } // if
//	} // for
//	return distinct;
//    }
//
//    public GenList<T> splice(int fromIndex, int toIndex){
//
//	if (fromIndex < 0 || toIndex > this.size || fromIndex > toIndex) {
//            throw new java.lang.IndexOutOfBoundsException("Invalid index");
//        } // if
//	GenList<T> splice = new LinkedGenList<>();
//	for(int i = fromIndex; i < toIndex; i++) {
//	    splice.add(this.get(i));
//	} // for
//
//	return splice;
//    }
//
//    public boolean contains(T o) {
//
//	if(o == null) {
//	    throw new java.lang.NullPointerException("Object is null");
//	}
//	Iterator<T> i = this.iterator();
//	while(i.hasNext()) {
//	    if(o.equals(i.next())) {
//		return true;
//	    }
//	}
//	return false;
//    }
//
//    
//    public String makeString(String sep) {
//	String result = "";
//	int check = this.size - 1;
//	Node<T> temp = this.gen;
//	for(int g = 0; g < this.size; g++) {
//	    if(g < check) {
//		result += temp.getVal() + sep;
//		temp = temp.getNext();
//	    } else {
//		result += temp.getVal();
//	    } // if-else
//	}
//
//	return result;
//    }
//
//    public String makeString() {
//	String result = this.makeString("");
//	return result;
//    }
//
//    public String makeString(String start, String sep, String end) {
//	String result = start + this.makeString(sep) + end;
//	return result;
//    }

    

}