package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;




@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	
	@OneToOne(fetch= FetchType.LAZY)
	
	private Password password;
	@Embedded
	private Address address;
	
	@ManyToMany
	@JoinTable(name="STUDENT_COURSE",joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	
	
	private List<Course> courses = new ArrayList<>();
	

	protected Student() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Password getPassport() {
		return password;
	}

	public void setPassport(Password password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}


	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}