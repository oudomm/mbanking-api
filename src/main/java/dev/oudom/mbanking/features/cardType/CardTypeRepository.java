package dev.oudom.mbanking.features.cardType;

import dev.oudom.mbanking.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {

    List<CardType> findAllByIsDeletedFalse();

    Optional<CardType> findByNameIgnoreCaseAndIsDeletedFalse(String name);
}
