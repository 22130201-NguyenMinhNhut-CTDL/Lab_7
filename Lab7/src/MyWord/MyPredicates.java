package MyWord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MyPredicates {
	// Remove every object, obj, from coll for which p.test(obj)
	// is true. (This does the same thing as coll.removeIf(p).)
	//xóa phần tử 
	public static <T> void remove(Collection<T> coll, Predicate<T> p) {
		coll.removeIf(p:: test);
	}

	// Remove every object, obj, from coll for which
	// pr.test(obj) is false. (That is, retain the
	// objects for which the predicate is true.)
	//xóa all phần tử khi test -> false
	public static <T> void retain(Collection<T> coll, Predicate<T> p) {
		coll.removeIf(obj -> !p.test(obj));
	}

	// Return a Set that contains all unique objects, obj,
	// from the collection, coll, such that p.test(obj)
	// is true.
	//trả về tất cả dt
	public static <T> Set<T> collect(Collection<T> coll, Predicate<T> p) {
		Set<T> set = new HashSet<>();
		//kiểm tra
		for (T obj : coll) {
			if (p.test(obj)) {
				set.add(obj);
				}
		}
		return set;
	}

	// Return the index of the first item in list
	// for which the predicate is true, if any.
	// If there is no such item, return -1.
	// trả về dt đầu tiên <> -1
	public static <T> int find(Collection<T> coll, Predicate<T> p) {
			int index = 0;
			for (T obj : coll) {
				if (p.test(obj)) {
					return index;
				}
				index++;
			}
		
		return -1;
	}
	public static void main(String[] args) {
		
		List<Integer> number = new LinkedList<>();
		number.add(1);
		number.add(2);
		number.add(3);
		number.add(3);
		number.add(4);
		number.add(5);
		
		Predicate<Integer> lenght = i -> i % 2 == 0;
		//test remove
		remove(number, lenght);
		System.out.println("Remove: " + number);
		
		//test retain
		retain(number, lenght);
		System.out.println("Retain: " + number);
		

		List<Integer> numbers = new LinkedList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		//test collect
		Set<Integer> set = collect(numbers, lenght);
		System.out.println("Collect: " + set);
		
		//test find
		int index = find(numbers, lenght);
		System.out.println("Find: " + index);
	}
}
