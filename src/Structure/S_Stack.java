package Structure;

import java.util.Arrays;
import java.util.EmptyStackException;

import StructureInterface.StackInterface;

public class S_Stack<E> implements StackInterface<E> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] EMPTY_ARRAY = {};

	private int size = 0;
	private Object[] array;
	
	public S_Stack() {
		array = EMPTY_ARRAY;
		size = 0;
	}
	
	public S_Stack(int capacity) {
		array = new Object[capacity];
		size = 0;
	}
	
	
	private void resize() {
		// 용적이 비었을 때
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 용적이 가득 찼을 떄
		if(size == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
			return;
		}
		
		// 사이즈가 용적크기의 절반 미만일 때
		if(size < array.length/2) {
			int new_capacity = array.length / 2;
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	@Override
	public E push(E value) {
		if(size == array.length) {
			resize();
		}
		
		array[size] = value;
		size++;
		
		return value;
	}
	
	@Override
	public E pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		@SuppressWarnings("unchecked")
		E data = (E) array[size-1];
		size--;
		resize();
		
		return data;
	}
	
	
	@Override
	public E peek() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		@SuppressWarnings("unchecked")
		E data = (E) array[size-1];
		
		return data;
	}
	
	
	@Override
	public int search(Object value) {
		for(int i=size-1; i>=0; i--) {
			if(value.equals(array[i])) {
				return size - i;
			}
		}
		
		return -1;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void clear() {
		for(int i=0; i<size-1; i++) {
			array[i] = null;
		}
		
		size = 0;
		resize();
	}
	
	@Override
	public boolean empty() {
		return size == 0 ? true : false;
	}
}
