package tn.esprit.spring.tpcafearoussiahassen.Services;

import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;

import java.util.List;

public interface IClientService {
    Client addClient(Client clt);
    List<Client> saveClient(List<Client> clients);
    Client selectClientByIdWithGet(long id);
    Client selectClientByIdWithOrElse(long id);
    List<Client> selectAllClients();
    void deleteClient(Client clt);
    void deleteAllClient();
    void deleteClientById(long id);
    long countingClients();
    boolean verifyClientById(long id);

    // -----------------------Méthodes avec DTO-----------
    ClientResponse addClientWithDTO(ClientRequest clientRequest);
    List<ClientResponse> saveClientsWithDTO(List<ClientRequest> clientRequests);
    ClientResponse selectClientByIdWithGetWithDTO(long id);
    ClientResponse selectClientByIdWithOrElseWithDTO(long id);
    List<ClientResponse> selectAllClientsWithDTO();
    void deleteClientWithDTO(ClientRequest clientRequest);
    Client addClientCc (Client clt);

    void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client);
}
