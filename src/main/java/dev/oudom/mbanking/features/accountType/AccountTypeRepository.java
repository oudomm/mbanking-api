package dev.oudom.mbanking.features.accountType;

import dev.oudom.mbanking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    List<AccountType> findAllByIsDeletedFalse();

    Optional<AccountType> findByAliasAndIsDeletedFalse(String alias);
}
