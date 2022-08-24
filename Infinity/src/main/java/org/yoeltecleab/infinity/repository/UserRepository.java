package org.yoeltecleab.infinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yoeltecleab.infinity.model.User;

/**
 * <pre>
 * This interface extends the JPA Repositories to implements the methods of the repository
 * It also allows to add custom methods with custom queries for database manipulation
 * </pre>
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u.firstName from User u where u.email = ?1")
    String name(String userEmail);

}
