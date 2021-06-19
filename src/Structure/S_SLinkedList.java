package Structure;

import java.util.NoSuchElementException;

import StructureInterface.ListInterface;

public class S_SLinkedList<E> implements ListInterface<E> {
	private S_Node<E> head;		// 노드의 첫 부분
	private S_Node<E> tail;		// 노드의 마지막 부분
	private int size;		// 요소 개수
	
	// 생성자
	public S_SLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public S_Node<E> search(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		S_Node<E> x = head;
		
		for(int i=0; i<index; i++) {
			x = x.next;
		}
		
		return x;
	}
	
	public void addFirst(E value) {
		S_Node<E> newNode = new S_Node<E>(value);
		
		newNode.next = head;
		head = newNode;
		size++;
		
		if(head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		if(tail.next == null) {
			addFirst(value);
		}
		
		S_Node<E> newNode = new S_Node<E>(value);
		
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public void add(int index, E value) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			addFirst(value);
			return;
		}

		if(index == size) {
			addLast(value);
			return;
		}
		
		S_Node<E> prev_node = search(index - 1);
		S_Node<E> next_node = prev_node.next;
		S_Node<E> new_node = new S_Node<E>(value);
		
		prev_node.next = new_node;
		new_node.next = next_node;
		size++;
	}
	
	
	public E remove() {
		S_Node<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		E element = headNode.data;
	
		head.data = null;
		head.next = null;
		
		head = head.next;
		size--;
		
		if(size == 0) {
			tail = null;
		}
		
		return element;
	}
	
	@Override
	public E remove(int index) {
		if(index == 0) {
			return remove();
		}
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		S_Node<E> prevNode = search(index - 1);
		S_Node<E> removeNode = prevNode.next;
		S_Node<E> nextNode = removeNode.next;
		
		E element = removeNode.data;
		
		prevNode.next = nextNode;
		
		removeNode.data = null;
		removeNode.next = null;
		size--;
		
		return element;
	}
	
	public boolean remove(Object value) {
		S_Node<E> prevNode = head;
		S_Node<E> x = head;
		boolean result = false;
		
		for(; x!=null; x=x.next) {
			if(value.equals(x.data)) {
				result = true;
				break;
			}
			
			prevNode = x;
		}
		
		if(result == false) {
			return result;
		} 
		else if(x.equals(head)) {
			remove();
			return result;
		}
		else {
			prevNode.next = x.next;
			x.data = null;
			x.next = null;
			size--;
			return result;
		}
	}
	
	@Override
	public E get(int index) {
		return search(index).data;
	}
	
	@Override
	public void set(int index, E value) {
		S_Node<E> oldNode = search(index);
		oldNode.data = null;
		oldNode.data = value;
	}
	
	@Override
	public int indexOf(Object value) {
		int index = 0;
		
		for(S_Node<E> x=head; x!=null; x=x.next) {
			if(value.equals(x.data)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}
	
	@Override
	public boolean contains(Object value) {
		return indexOf(value) >= 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		for(S_Node<E> x=head; x!=null;) {
			S_Node<E> nextNode = x;
			x.data = null;
			x.next = null;
			x = nextNode.next;
		}
		head = tail = null;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException {
		S_SLinkedList<? super E> sl = (S_SLinkedList<? super E>) super.clone();
		
		sl.head = null;
		sl.tail = null;
		sl.size = 0;
		
		for(S_Node<E> x=head; x!=null; x=x.next) {
			sl.addLast(x.data);
		}
		
		return sl;
	}
}
