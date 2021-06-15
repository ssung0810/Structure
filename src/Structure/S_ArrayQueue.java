package Structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import StructureInterface.QueueInterface;

public class S_ArrayQueue<E> implements QueueInterface<E>, Cloneable {
	// 최소 용적크기
	private static final int DEFAULT_CAPACITY = 64;
	
	private Object[] array;
	private int size;
	
	private int front;
	private int rear;
	
	// 생성자 1
	public S_ArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 생성자 2
	public S_ArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	private void resize(int newCapacity) {
		int arrayCapacity = array.length;	// 현재 용적 크기

		Object[] newArray = new Object[newCapacity];	// 용적을 변경한 배열
		
		/* 
		 * i = new array index
		 * j = original array
		 * index 요소개수(size)만큼 새 배열에 값 복사
		 */
		for(int i=1, j=front+1; i<=size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null;
		this.array = newArray;	// 새 배열을 기존 array의 배열로 덮어씌움
		
		front = 0;
		rear = size;
	}
	
	@Override
	public boolean offer(E item) {
		//용적이 가득 찼을 경우
		if((rear + 1) % array.length == front) {
			resize(array.length*2);
		}
		
		rear = (rear+1) % array.length; 	// rear을 rear의 다음 위치로 갱신
		
		array[rear] = item;
		size++;		// 사이즈 1증가
		
		return true;
	}
	
	@Override
	public E poll() {
		if(size == 0) {
			return null;
		}
		
		front = (front + 1) % array.length;
		
		@SuppressWarnings("unchecked")
		E item = (E)array[front];
		
		array[front] = null;
		size--;
		
		if(array.length > DEFAULT_CAPACITY && size < (array.length/4)) {
			resize(Math.max(DEFAULT_CAPACITY, array.length/2));
		}
		
		return item;
	}
	
	
	public E remove() {
		E item = poll();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	
	@Override
	public E peek() {
		if(size == 0) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E)array[(front+1) % array.length];
		
		return item;
	}
	
	public E element() {
		E item = peek();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean constains(Object value) {
		int start = (front + 1) % array.length;
		
		/* i : 요소 개수만큼 반환한다.
		 * idx : 원소 위치로, 매 회(idx + 1) % array.length; 의 위치로 갱신
		 */
		for(int i=0, idx=start; i<size; i++, idx=(idx+1)%array.length) {
			if(array[idx].equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void clear() {
		for(int i=0; i<array.length; i++) {
			array[i] = null;
		}
		
		front = rear = size = 0;
	}
	
	
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		final T[] res;
		
		// 들어오는 배열의 길이가 큐의 요소개수보다 작은 경우
		if(a.length < size) {
			if(front <= rear) {
				return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
			}
			
			res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
			int rearlength = array.length - front - 1;	// 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front+1 , res, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, res, rearlength, rear+1);
			
			return res;
		}
		
		if(front <= rear) {
			System.arraycopy(array, front+1, a, 0, size);
		} else {
			int rearlength = array.length - front - 1;	// 뒷 부분의 요소개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front + 1, a, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, a, rearlength, rear + 1);
		}
		
		return a;
	}
	
	
	public Object colne() {
		// super.clone 은 CloneNotSupportException 예외를 처리해주어야 한다.
		try {
			@SuppressWarnings("unchecked")
			S_ArrayQueue<E> clone = (S_ArrayQueue<E>) super.clone();	// 새로운 큐 객체 생성
			
			// 새로운 큐의 배열도 생성해주어야 함(내부 객체는 깊은복사가 되지 않기 때문)
			clone.array = Arrays.copyOf(array, array.length);
			return clone;
		} catch(CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	
	public void sort() {
		/* Comporator를 넘겨주지 않는 경우 해당객체의 Comporable에 구현된 정렬방식을 사용한다.
		 * 만약 구현되어있지 않으면 cannot be cast to class java.lang.Comporable 에러가 발생한다.
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
		 */
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		// null 접근 방지를 위해 toArray로 요소만 있는 배열을 얻어 이를 정렬한 뒤 덮어씌운다.
		Object[] res = toArray();
		
		Arrays.sort((E[]) res, 0, size, c);
		
		clear();
		/* 정렬된 원소를 다시 array에 0부터 차례대로 채운다.
		 * 이 떄 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야한다.
		 */
		System.arraycopy(res, 0, array, 1, res.length);
		
		this.rear = this.size = res.length;
	}
}
