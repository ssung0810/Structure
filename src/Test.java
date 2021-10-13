import Structure.S_HashSet;

public class Test {
	public static void main(String[] args) {
		S_HashSet<Integer> s = new S_HashSet<>();
		S_HashSet<Integer> a = new S_HashSet<>();

		s.add(10);
		a.add(10);

		System.out.println(a.equals(s));
	}
}