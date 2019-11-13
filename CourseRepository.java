package jpa;




import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository

@Transactional
public class CourseRepository {
	private Logger logger= LoggerFactory.getLogger(this.getClass());

	
	
	@Autowired 
	private EntityManager em;
	
	public Course findById(Long id){
		return em.find(Course.class, id);
	
}
	public void deleteById(Long id){
		Course course = findById(id);
		em.remove(course);
}
	
public Course save(Course c) {
		
		if (c.getId()==null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
		
		return c;
	}

public void playWithEntityManager()
{
	Course course1 = new Course("Web Services in 100 Steps");
	em.persist(course1);
	
	Course course2 = new Course("JPA in 50 Steps-updated");
	em.persist(course2);
	
}

public void addHardcodedReviewsForCourse() {
	//get the course 10003
	Course course = findById(10003L);
	logger.info("course.getReviews() -> {}", course.getReviews());
	
	//add 2 reviews to it
	Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");	//using enum values.
	Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");//using enum values.
	
	//setting the relationship
	course.addReview(review1);
	review1.setCourse(course);
	
	course.addReview(review2);
	review2.setCourse(course);
	
	//save it to the database
	em.persist(review1);
	em.persist(review2);
}
public void addReviewsForCourse(Long courseId, List<Review> reviews) {		
	Course course = findById(courseId);
	logger.info("course.getReviews() -> {}", course.getReviews());
	for(Review review:reviews)
	{			
		//setting the relationship
		course.addReview(review);
		review.setCourse(course);
		em.persist(review);
	}

}
}
