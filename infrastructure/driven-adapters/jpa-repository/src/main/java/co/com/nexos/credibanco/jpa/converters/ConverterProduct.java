package co.com.nexos.credibanco.jpa.converters;

import co.com.nexos.credibanco.jpa.card.CardData;
import co.com.nexos.credibanco.jpa.client.ClientData;
import co.com.nexos.credibanco.jpa.product.ProductData;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.product.Product;

import java.util.List;

public class ConverterProduct {
    private ConverterProduct() {
        throw new IllegalStateException("Utility Class");
    }

    public static Product convertProductDataToProduct(ProductData productData) {


        return productData!= null ? Product.builder()
                .productId(productData.getProductId())
                .client(ConverterClient.convertClientDataToClient(productData.getClient()))
                 .cardId(productData.getCardId())
                .build() : Product.builder().build();

    }

    public static List<Product> convertProductsDataToProducts(List<ProductData> productDataList) {
        return productDataList.stream().map(ConverterProduct::convertProductDataToProduct).toList();
    }

    public static ProductData convertProductToProductData(Product product) {
        ProductData productData = new ProductData();


        if(product!=null){
            productData.setProductId(product.getProductId());
           productData.setCardId(product.getCardId());
            productData.setClient(ConverterClient.convertClientToClientData(product.getClient()));
        }
        return productData;
    }

    public static List<ProductData> convertProductsToProductsData(List<Product> productList) {
        return productList.stream().map(ConverterProduct::convertProductToProductData).toList();
    }
}
