import Structure.S_Stack;

public class Test {

	public static void main(String[] args) {
		S_Stack<Integer> s = new S_Stack<Integer>();
		
		s.push(100);
		System.out.println(s.size());
		System.out.println(s.peek());
		System.out.println(s.empty());
		s.clear();
		System.out.println(s.push(200));
		System.out.println(s.pop());
		System.out.println(s.push(100));
		System.out.println(s.push(200));
		System.out.println(s.push(300));
		System.out.println(s.search(200));
	}

}
