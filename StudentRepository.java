package jpa;

import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository

@Transactional
public class StudentRepository {
	private Logger log= LoggerFactory.getLogger(this.getClass());

	
	
	@Autowired 
	EntityManager em;
	
	public Student findbyid(Long id){
		return em.find(Student.class, id);
	
}
	public void deleteById(Long id){
		Student student = findbyid(id);
		em.remove(student);
}
	
public Student save(Student c) {
		
		if (c.getId()==null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
		
		return c;
	}
public void saveStudentWithPassport() {
	Password passport = new Password("Z123456");
	em.persist(passport);

	Student student = new Student("Mike");

	student.setPassport(passport);
	em.persist(student);	
}

public void insertHardcodedStudentAndCourse(){
	Student student = new Student("Jack");
	Course course = new Course("Microservices in 100 Steps");
	em.persist(student);
	em.persist(course);
	
	student.addCourse(course);
	course.addStudent(student);
	em.persist(student);
}
public void insertStudentAndCourse(Student student, Course course){
	
	
	student.addCourse(course);
	course.addStudent(student);

	em.persist(student);
	em.persist(course);
}

}
