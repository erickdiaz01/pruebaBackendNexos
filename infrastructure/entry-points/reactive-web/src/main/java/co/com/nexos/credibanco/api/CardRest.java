package co.com.nexos.credibanco.api;

import co.com.nexos.credibanco.model.card.BalanceCard;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.CardId;
import co.com.nexos.credibanco.model.card.TypeOfCard;
import co.com.nexos.credibanco.usecase.card.activatecard.ActivateCardUseCase;
import co.com.nexos.credibanco.usecase.card.blockcard.BlockCardUseCase;
import co.com.nexos.credibanco.usecase.card.consultbalance.ConsultBalanceUseCase;
import co.com.nexos.credibanco.usecase.card.generatecardid.GenerateCardIdUseCase;
import co.com.nexos.credibanco.usecase.card.listcards.ListCardsUseCase;
import co.com.nexos.credibanco.usecase.card.rechargebalance.RechargeBalanceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardRest {
    private final ActivateCardUseCase activateCardUseCase;
    private final BlockCardUseCase blockCardUseCase;
    private final ConsultBalanceUseCase consultBalanceUseCase;
    private final GenerateCardIdUseCase generateCardIdUseCase;
    private final RechargeBalanceUseCase rechargeBalanceUseCase;
    private final ListCardsUseCase listCardsUseCase;

    @GetMapping("/{productId}/number")
    public Mono<Card> generateCard(@PathVariable Integer productId, @RequestBody TypeOfCard type) {
        return generateCardIdUseCase.generateCardId(productId, type);
    }

    @GetMapping
    public Flux<Card> listCards() {
        return listCardsUseCase.listCards();
    }

    @PostMapping("/enroll")
    public Mono<Card> activateCard(@RequestBody CardId cardId) {
        return activateCardUseCase.activateCard(cardId.getCardId());
    }

    @DeleteMapping("/{cardId}")
    public Mono<Card> blockCard(@PathVariable String cardId) {
        return blockCardUseCase.blockCard(cardId);
    }

    @PostMapping("/balance")
    public Mono<Card> rechargeBalance(@RequestBody BalanceCard card) {
        return rechargeBalanceUseCase.rechargeBalance(card.getCardId(), card.getBalance());
    }

    @GetMapping("/balance/{cardId}")
    public Mono<Integer> consultBalance(@PathVariable String cardId) {
        return consultBalanceUseCase.consultBalance(cardId);
    }
}
