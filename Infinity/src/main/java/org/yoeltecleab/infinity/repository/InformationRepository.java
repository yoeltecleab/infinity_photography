package org.yoeltecleab.infinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yoeltecleab.infinity.model.Information;
import org.yoeltecleab.infinity.model.User;

import java.util.List;

/**
 * <pre>
 * This interface extends the JPA Repositories to implements the methods of the repository
 * It also allows to add custom methods with custom queries for database manipulation
 * </pre>
 */
@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {
    List<Information> findAllByUser_Email(String userEmail);

    Information findByBookingId(int infoId);

    /* This is for testing purpose only */
    Information findByUser_Email(String userEmail);

    @Transactional
    @Modifying
    @Query("delete from Information i where i.bookingId = ?1")
    void deleteByBookingId(int bookingId);


    @Transactional
    @Modifying
    @Query("delete from Information i where i.user = ?1")
    void deleteInformationByUser(User user);

    @Query("select SUM(i.totalPrice) from Information i where i.user.email = ?1")
    Double allTotalPrice(String userEmail);


}
