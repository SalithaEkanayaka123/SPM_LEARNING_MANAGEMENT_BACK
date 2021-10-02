package lk.spm.learning.management.repository;

import lk.spm.learning.management.model.AnnouncementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorAnnouncementRepository extends JpaRepository<AnnouncementModel, String> {
}
