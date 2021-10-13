package Structure;

class S_Node<E> {
	// hash와 key값은 변하지 않으므로 final로 선언해준다.
	final int hash;
	final E key;
	S_Node<E> next;
	
	public S_Node(int hash, E key, S_Node<E> next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
	}
}
