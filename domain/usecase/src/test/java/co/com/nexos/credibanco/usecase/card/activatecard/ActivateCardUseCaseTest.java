package co.com.nexos.credibanco.usecase.card.activatecard;

import co.com.nexos.credibanco.model.card.gateways.CardRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActivateCardUseCaseTest {
  @Mock
  private CardRepository cardRepository;

  @InjectMocks
  private ActivateCardUseCase activateCardUseCase;

  @Test
  void activateCard() {

    activateCardUseCase.activateCard("1000011234567890");

    Mockito.verify(cardRepository).activeCard("1000011234567890");
  }
}