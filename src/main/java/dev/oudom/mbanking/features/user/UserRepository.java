package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNationalCardId(String nationalCardId);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByStudentIdCard(String studentIdCard);
}
