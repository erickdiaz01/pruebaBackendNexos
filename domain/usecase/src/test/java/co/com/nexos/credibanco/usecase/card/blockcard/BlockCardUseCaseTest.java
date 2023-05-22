package co.com.nexos.credibanco.usecase.card.blockcard;

import co.com.nexos.credibanco.model.card.gateways.CardRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlockCardUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private BlockCardUseCase blockCardUseCase;

    @Test
    void blockCard() {
        blockCardUseCase.blockCard("1000011234567890");

        Mockito.verify(cardRepository).blockCard("1000011234567890");

    }
}