package StructureInterface;

public interface QueueInterface<E> {
	/* 큐에 가장 마지막에 요소를 추가합니다.
	 * 
	 * @param : 큐에 추가 할 요소
	 * @return : 큐에 요소가 정상적으로 추가되었을 때 ture반환
	 */
	boolean offer(E e);
	
	/* 큐의 첫 번째 요소를 삭제하고 삭제 된 요소를 반환합니다.
	 * 
	 * @return : 큐의 삭제 된 요소 반환
	 */
	E poll();
	
	/* 큐의 첫 번째 요소를 반환합니다.
	 * 
	 * @return : 큐의 첫 번째 요소 반환
	 */
	E peek();
}
