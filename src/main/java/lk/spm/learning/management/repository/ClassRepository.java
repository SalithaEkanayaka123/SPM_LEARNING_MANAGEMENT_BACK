package lk.spm.learning.management.repository;

import lk.spm.learning.management.model.Class;
import lk.spm.learning.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
