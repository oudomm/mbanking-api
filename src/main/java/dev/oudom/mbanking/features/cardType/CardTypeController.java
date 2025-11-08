package dev.oudom.mbanking.features.cardType;

import dev.oudom.mbanking.features.cardType.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card-types")
@RequiredArgsConstructor
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @GetMapping
    public List<CardTypeResponse> getAllCardTypes() {
        return cardTypeService.findAllCardTypes();
    }

    @GetMapping("/name/{name}")
    public CardTypeResponse getCardTypeByName(@PathVariable String name) {
        return cardTypeService.findCardTypeByName(name);
    }
}
