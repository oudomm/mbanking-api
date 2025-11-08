package dev.oudom.mbanking.features.cardType;

import dev.oudom.mbanking.domain.CardType;
import dev.oudom.mbanking.features.cardType.dto.CardTypeResponse;
import dev.oudom.mbanking.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService {

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Override
    public List<CardTypeResponse> findAllCardTypes() {
        List<CardType> cardTypes = cardTypeRepository.findAllByIsDeletedFalse();

        return cardTypes.stream().map(cardTypeMapper::toCardTypeResponse).toList();
    }

    @Override
    public CardTypeResponse findCardTypeByName(String name) {
        CardType cardType = cardTypeRepository
                .findByNameIgnoreCaseAndIsDeletedFalse(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card type not found"));

        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
