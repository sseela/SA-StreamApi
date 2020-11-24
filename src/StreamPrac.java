import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPrac {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("sand");
		list.add("muneer");
		list.add("srinivas");
		list.add("suraj");
		System.out.println(list);
		
		List<String> filter = list.stream().filter(s -> s.length() == 5).collect(Collectors.toList());
		System.out.println(filter);
		
		List<String> map = list.stream().map(s -> s.concat("111")).collect(Collectors.toList());
		System.out.println(map);
		
		List<String> sorted = list.stream().sorted().collect(Collectors.toList());
		System.out.println(sorted);
		
		List<String> customSorted = list.stream().sorted((s1, s2) -> s2.compareTo(s1)).collect(Collectors.toList());
		System.out.println(customSorted);
		
		/*list.stream().sorted(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 1;
			}
		});*/
		
		Long length = list.stream().filter(s -> s.length() == 5).count();
		System.out.println(length);
		
		String min = list.stream().min((s1, s2) -> s2.compareTo(s1)).get();
		System.out.println(min);
		
		String max = list.stream().max((s1, s2) -> s2.compareTo(s1)).get();
		System.out.println(max);
		
		list.stream().forEach(System.out::println);
		
		list.stream().forEach((s1) -> System.out.println(s1));
		
		Set set = list.stream().sorted().collect(Collectors.toSet());
		System.out.println(set);
		
		Map m = list.stream().collect(Collectors.toMap((s1)-> s1, (v1)->v1));
		System.out.println(m);
		
		
		class Employee {
			private Integer id;
			private String name;
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			Employee(Integer id, String name){
				this.id = id;
				this.name = name;
			}
			public String toString() {
				return this.name+"-"+this.id;
			}
		}
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(123, "sandeep"));
		empList.add(new Employee(456, "muneer"));
		empList.add(new Employee(789, "srinivas"));
		/*Map<Integer, String> m2 = empList.stream().collect(Collectors.toMap((e1 Employee) -> e1., valueMapper));
		System.out.println(m2);*/
		
		List<Employee> empList1 = empList.stream().sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList());
		System.out.println(empList1);
		
		Long countEmp = empList.stream().count();
		System.out.println(countEmp);
		
		List<Employee> pp = empList.stream().distinct().collect(Collectors.toList());
		System.out.println(pp);
		
		
		
		
		Map<String, Employee> m11 = new HashMap<>();
		m11.put("first", new Employee(123, "rupesh"));
		m11.put("second", new Employee(546, "shabaz"));
		m11.put("third", new Employee(978, "shanmukh"));
		m11.put("third", new Employee(233, "sandeep"));
		/*System.out.println(m11);*/
		// we can't use stream on Map object bcoz map is not a child of Collection
		m11.entrySet().stream().forEach(System.out::println);
		
		/*for(Map.Entry<String, Employee> m1: m11.entrySet()) {
			if(m1.getKey().equals("third")) {
				m1.setValue(new Employee(967, "shannu"));
			}
		}*/
		Set entrySet = m11.entrySet();
		Iterator itr = entrySet.iterator();
		if(itr.hasNext()) {
			Map.Entry<String, Employee> entry= (Map.Entry<String, Employee>) itr.next();
			if(entry.getKey().equals("third")) {
				entry.setValue(new Employee(234, "shannu"));
			}
		}
		System.out.println(m11);
		
		Set<String> s1 = m11.keySet();
		
		for(Map.Entry<String, Employee> entry:m11.entrySet()) {
			if(entry.getKey().equals("third")) {
				entry.setValue(new Employee(333, "sandy"));
			}
		}
		System.out.println(m11);
		
		System.out.println(m11.entrySet().stream().count());
		m11.entrySet().forEach(e -> System.out.println(e.getKey()));
		/*System.out.println(m11.values());
		System.out.println(s1);
		System.out.println(m11.entrySet());*/
		
		
		
		Set<String> sank = list.stream().map(s -> s.concat("VAS")).collect(Collectors.toSet());
		System.out.println(sank);
		
		Set<String> sank1 = sank.stream().filter(s -> s.contains("m")).collect(Collectors.toSet());
		System.out.println(sank1);
		
		
		
		TreeSet<Employee> t = new TreeSet<Employee>((e1, e2) -> e1.getName().compareTo(e2.getName()));   // this is as good as creating an implementation class for comparator and creating new object
		t.add(new Employee(111, "rupesh"));															// treeset second kind of constructor will take comparator object which is FI. so we can use lambda expression and provide it new Comparator()
		t.add(new Employee(222, "sasi"));
		t.add(new Employee(333, "pavan"));
		
		System.out.println(t);
		List<Employee> llll = t.stream().filter(e -> e.getName().contains("s")).collect(Collectors.toList());
		System.out.println(llll);
		
		
		System.out.println(t.stream().map(e -> e.getId()+" "+e.getName()).collect(Collectors.toList()));
		
		//--- different ways to convert an array to list in java
		/*List<String> strings = Arrays.asList(arr);
		List<String> strings = Arrays.stream(arr).collect(Collectors.toList());
		List<String> strings = Stream.of(arr).collect(Collectors.toList());*/
		
		// not only on collection object we can create stream object, we can create like
		
		Stream<Integer> si = Stream.of(1,2,3);
		System.out.println(si.collect(Collectors.toList()));
		
		String[] sii = {"AJ", "Varun", "Singh"};
		System.out.println(Stream.of(sii).collect(Collectors.toSet()));
		
		Stream.of(t.toArray()).forEach(System.out::println);
		
		list.sort(new Comparator() {									// anonymous inner functions
			public int compare(Object o1, Object o2) {
				String s3 = (String)o1;
				String s4 = (String)o2;
				return s3.compareTo(s4);
			}
		});
		
		// or
		
		list.sort((s10, s20)-> s10.compareTo(s20));						// lambda expression
	}

}
