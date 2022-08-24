package org.yoeltecleab.infinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yoeltecleab.infinity.model.Services;

/**
 * <pre>
 * This interface extends the JPA Repositories to implements the methods of the repository
 * It also allows to add custom methods with custom queries for database manipulation
 * </pre>
 */
@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {
    Services findByOccasion(String occasion);
}
