package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.S3File;

@Repository
public interface ImageRepository extends JpaRepository<S3File, Long> {
}
