package dev.oudom.mbanking.mapper;

import dev.oudom.mbanking.domain.CardType;
import dev.oudom.mbanking.features.cardType.dto.CardTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardTypeResponse toCardTypeResponse(CardType cardType);
}
