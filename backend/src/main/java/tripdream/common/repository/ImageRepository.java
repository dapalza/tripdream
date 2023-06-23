package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
