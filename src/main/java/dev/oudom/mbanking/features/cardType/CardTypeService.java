package dev.oudom.mbanking.features.cardType;

import dev.oudom.mbanking.features.cardType.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {

    List<CardTypeResponse> findAllCardTypes();

    CardTypeResponse findCardTypeByName(String name);
}
