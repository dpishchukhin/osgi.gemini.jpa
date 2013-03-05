package at.emundo.osgi.module2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value="entityTwoRepository")
public interface EntityTwoRepository extends JpaRepository<EntityTwo, Long> {
}
