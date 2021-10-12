package StructureInterface;

public interface SetInterface<E> {
    // 추가(중복은 안됨)
    boolean add(E e);

    // 삭제
    boolean remove(Object o);

    // 집합에 들어있는지 여부
    boolean contains(Object o);

    // 지정된 객체가 현재 집합과 같은지
    boolean equals(Object o);

    // 집합이 비었는지 체크
    boolean isEmpty();

    // 집합의 개수
    int size();

    // 집합의 모든 요소 제거
    void clear();
}
