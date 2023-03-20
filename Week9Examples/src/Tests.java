import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Employee e1 = new Employee(new Date(), "Jhon Smith", "1234");
		TimeUnit.SECONDS.sleep(3);
		Employee e3 = new Employee(new Date(), "Pancho Montecino", "12345");
		TimeUnit.SECONDS.sleep(3);
		Employee e2 = new Employee(new Date(), "Guaton Nelson", "123");

		// HireDataComparator comparator = new HireDataComparator();
		// System.out.println(comparator.compare(e1, e2));
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
//		System.out.println(employees);
		for (Employee e : employees)
			System.out.println(e);

		Collections.sort(employees, new HireDataComparator());
		System.out.println("ordered...");
		for (Employee e : employees)
			System.out.println(e);
//		System.out.println(employees);
//		unsortedPriorityQueue(employees);
	}

	

}
