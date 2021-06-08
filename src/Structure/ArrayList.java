package Structure;

import java.util.Arrays;

import StructureInterface.ListInterface;

public class ArrayList<E> implements ListInterface {
	private static final int DEFAULT_CAPACITY = 10;		// 최소(기본) 용적 크기
	private static final Object[] EMPTY_ARRAY = {};		// 빈 배열
	
	private int size;	// 요소 개수
	
	Object[] array;		// 요소를 담을 배열
	
	// 생성자1 (초기 공간 할당 x)
	public ArrayList() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2 (초기 공간 할당 o)
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	
	private void resize() {
		int array_capacity = array.length;
		
		// if array's capacity is 0
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 용량이 꽉 찰 경우
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			
			// copy
			array = Arrays.copyOf(array, new_capacity);
			return;
		}
		
		// 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (array_capacity / 2)) {
			int new_capacity = array_capacity / 2;
			
			// copy
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		// 꽉 차있는 상태라면 용적 재 할당
		if(size == array.length) {
			resize();
		}
		
		array[size] = value;	// 마지막 위치에 요소추가
		size++;		// 사이즈 1증가
	}
	
	@Override
	public void add(int index, E value) {
		if(index > size || index < 0) {		// 영역을 벗어 날 경우 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		if(index == size) {		// index 가 마지막 위치라면 addLast 메소드로 요소 추가
			addLast(value);
		} else {
			if(size == array.length) {	// 꽉 차있다면 용적 재 할당
				resize();
			}
			
			// index 기준 후자에 있는 모든 요소들 한칸 씩 뒤로 이동
			for(int i=size; i>index; i--) {
				array[i] = array[i-1];
			}
			
			array[index] = value;
			size++;
		}
	}
	
	public void addFirst(E value) {
		add(0, value);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// Object 타입에서 E 타입으로 캐스팅 후 반환
		return (E) array[index];
	}
	
	
	@Override
	public void set(int index, E value) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			// 해당위치에 요소를 교체
			array[index] = value;
		}
	}
	
	
	@Override
	public int indexOf(Object value) {
		// value 와 같은 객체(요소 값)일 경우 i(위치) 반환
		for(int i=0; i<size; i++) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public int lastIndexOf(Object value) {
		// value 와 같은 객체(요소 값)일 경우 i(위치) 반환
		for(int i=size-1; i>=0; i--) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	@Override
	public boolean contains(Object value) {
		// 0이상이면 요소가 존재한다는 뜻
		if(indexOf(value) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
