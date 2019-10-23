package cs1302;
public class Node <T> {
    private T val;
    private Node<T> next;
    public Node () {

	this.val = null;
	this.next = null;
    }

    public Node (T val) {

	this.val = val;
	this.next = null;
    }

    public Node<T> getNext () {
	return this.next;
    }

    public void setNext(Node<T> some) {
	this.next = some;
    }

    public void setVal(T value) {

	this.val = value;
    }

    public T getVal() {
	return this.val;
    }
}
