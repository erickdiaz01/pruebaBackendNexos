package co.com.nexos.credibanco.jpa.converters;

import co.com.nexos.credibanco.jpa.client.ClientData;
import co.com.nexos.credibanco.model.client.Client;

import java.util.List;

public class ConverterClient {

    private ConverterClient() {
        throw new IllegalStateException("Utility Class");
    }

    public static Client convertClientDataToClient(ClientData clientData) {
        return clientData!= null ? Client.builder()
                .clientId(clientData.getClientId())
                .fullName(clientData.getFullName())
                .build() : Client.builder().build();

    }

    public static List<Client> convertClientsDataToClients(List<ClientData> clientDataList) {
        return clientDataList.stream().map(ConverterClient::convertClientDataToClient).toList();
    }

    public static ClientData convertClientToClientData(Client client) {
        ClientData clientData = new ClientData();
        if(client!= null){
            clientData.setClientId(client.getClientId());
            clientData.setFullName(client.getFullName());
        }
        return clientData;
    }

    public static List<ClientData> convertClientsToClientsData(List<Client> clients) {
        return clients.stream().map(ConverterClient::convertClientToClientData).toList();
    }





}
