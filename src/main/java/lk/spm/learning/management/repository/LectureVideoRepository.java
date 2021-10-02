package lk.spm.learning.management.repository;
import lk.spm.learning.management.model.LectureVideos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureVideoRepository extends JpaRepository<LectureVideos, String> {

}
