package lk.spm.learning.management.repository;

import lk.spm.learning.management.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, String> {
}
