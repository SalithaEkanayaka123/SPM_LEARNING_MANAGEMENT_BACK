package lk.spm.learning.management.repository;

import lk.spm.learning.management.model.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {

}
