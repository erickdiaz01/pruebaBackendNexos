package co.com.nexos.credibanco.jpa.services;

import co.com.nexos.credibanco.model.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class generateCardNumbers {

    public String generate(String productId){
        String randomNumbers = UUID.randomUUID().toString().replaceAll("-", "").substring(0,9);
        return productId.concat(randomNumbers);
    }
}
