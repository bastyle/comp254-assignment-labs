package maps;

public class Student {

	private String name;
	private int idNumber;

	public Student(String name, int idNumber) {
		super();
		this.name = name;
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

}
