package youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import youtube.model.Thumbnail;

@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
}
