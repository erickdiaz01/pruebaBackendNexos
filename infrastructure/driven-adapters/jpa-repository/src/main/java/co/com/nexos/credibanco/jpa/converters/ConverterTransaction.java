package co.com.nexos.credibanco.jpa.converters;

import co.com.nexos.credibanco.jpa.transaction.TransactionData;

import co.com.nexos.credibanco.model.transaction.Transaction;

import java.util.List;

public class ConverterTransaction {
    private ConverterTransaction() {
        throw new IllegalStateException("Utility Class");
    }

    public static Transaction convertTransactionDataToTransaction(TransactionData transactionData) {
        return transactionData != null ? Transaction.builder()
                .transactionId(transactionData.getTransactionId())
                .card(ConverterCard.convertCardDataToCard(transactionData.getCardData()))
                .transactionDate(transactionData.getTransactionDate())
                .isValid(transactionData.getIsValid())
                .price(transactionData.getPrice())
                .build() : Transaction.builder().build();

    }

    public static List<Transaction> convertTransactionsDataToTransactions(List<TransactionData> transactionDataList) {
        return transactionDataList.stream().map(ConverterTransaction::convertTransactionDataToTransaction).toList();
    }

    public static TransactionData convertTransactionToTransactionData(Transaction transaction) {
        TransactionData transactionData = new TransactionData();
        if (transaction != null) {
            transactionData.setTransactionId(transaction.getTransactionId());
            transactionData.setCardData(ConverterCard.convertCardToCardData(transaction.getCard()));
            transactionData.setTransactionDate(transaction.getTransactionDate());
            transactionData.setIsValid(transaction.getIsValid());
            transactionData.setPrice(transaction.getPrice());
        }
        return transactionData;
    }

    public static List<TransactionData> convertTransactionsToTransactionsData(List<Transaction> transactionList) {
        return transactionList.stream().map(ConverterTransaction::convertTransactionToTransactionData).toList();
    }
}
