package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNationalCardId(String nationalCardId);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByStudentIdCard(String studentIdCard);

    List<User> findAllByIsDeletedFalseAndIsBlockedFalse();

    Optional<User> findByPhoneNumber(String phoneNumber);

    // Optional<User> findByUuid(String uuid);

    // @Query(value = "SELECT * FROM users WHERE uuid = ?1", nativeQuery = true)

    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    @Transactional
    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

    @Transactional
    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = TRUE WHERE u.uuid = ?1")
    void disableByUuid(String uuid);

    @Transactional
    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = FALSE WHERE u.uuid = ?1")
    void enableByUuid(String uuid);
}
