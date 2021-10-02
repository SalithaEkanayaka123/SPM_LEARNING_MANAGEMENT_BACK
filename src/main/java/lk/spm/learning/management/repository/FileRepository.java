package lk.spm.learning.management.repository;
import lk.spm.learning.management.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel, String> {

}
