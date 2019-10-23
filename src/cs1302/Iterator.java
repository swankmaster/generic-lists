package cs1302;
import cs1302.genlistadt.*;

public class Iterator<T> implements java.util.Iterator<T>{
    
    private T obj;
    private GenList<T> list;
    private int index = 0;
    
    public Iterator(GenList<T> iter){

	this.list = iter;
	this.obj = iter.get(index);

    }
    
    public T next() {
    this.obj = this.list.get(this.index);
    this.index++;
	return this.obj;
    }

    public boolean hasNext() {

    	try {
	if(this.list.get(index) == null) {
	    return false;
	}
    	}
    	catch (java.lang.IndexOutOfBoundsException ioobe) {
    		return false;
    	}
	return true;
    }

    
}
