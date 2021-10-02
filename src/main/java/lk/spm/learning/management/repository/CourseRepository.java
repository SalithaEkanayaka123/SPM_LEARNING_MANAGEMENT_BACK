package lk.spm.learning.management.repository;

import lk.spm.learning.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    long deleteByTitle(String title);
}

