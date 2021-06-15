import java.util.Arrays;
import java.util.Comparator;

import Structure.S_ArrayQueue;

public class Test {

	public static void main(String[] args) {
		S_ArrayQueue<Student> s = new S_ArrayQueue<>();
		
		s.offer(new Student("aaa", 50));
		s.offer(new Student("bbb", 20));
		s.offer(new Student("ccc", 70));
		s.offer(new Student("ddd", 40));
		
		s.sort();
		
		for(Object a : s.toArray()) {
			System.out.println(a);
		}
	}
	
//	static Comparator<Student> comp = new Comparator<Student>() {
//		@Override
//		public int compare(Student o1, Student o2) {
//			return o2.score - o1.score;
//		}
//	};

}

class Student implements Comparable<Student> {
	String name;
	int score;
	
	Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return "이름: " + name + "\t성적: " + score;
	}
	
	@Override
	public int compareTo(Student o) {
		return o.score - this.score;
	}
}
