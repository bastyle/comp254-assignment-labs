import java.util.Date;

public class Employee {

	private Date hireDate;
	private String fullName;
	private String socialInsuranceNumer;

	public Employee(Date hireDate, String fullName, String socialInsuranceNumer) {
		super();
		this.hireDate = hireDate;
		this.fullName = fullName;
		this.socialInsuranceNumer = socialInsuranceNumer;
	}

	public Employee(Date hireDate) {
		super();
		this.hireDate = hireDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSocialInsuranceNumer() {
		return socialInsuranceNumer;
	}

	public void setSocialInsuranceNumer(String socialInsuranceNumer) {
		this.socialInsuranceNumer = socialInsuranceNumer;
	}

	@Override
	public String toString() {
		return "Employee [hireDate=" + hireDate + ", fullName=" + fullName + ", socialInsuranceNumer="
				+ socialInsuranceNumer + "]";
	}

}
