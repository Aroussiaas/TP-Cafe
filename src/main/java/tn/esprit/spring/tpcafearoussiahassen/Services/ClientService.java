package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;
import tn.esprit.spring.tpcafearoussiahassen.mappers.ClientMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.AdresseRepository;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
//@Component
@AllArgsConstructor

public class ClientService implements IClientService{

    ClientRepository repo;
    ClientMapper mapper;
    AdresseRepository adresseRepository;


    @Override
    public Client addClient(Client clt) {
        return repo.save(clt);
    }

    @Override
    public List<Client> saveClient(List<Client> clients) {
        return repo.saveAll(clients);
    }

    @Override
    public Client selectClientByIdWithGet(long id) {
        return repo.findById(id).get();
    }

    @Override
    public Client selectClientByIdWithOrElse(long id) {
        Client fakeClient = Client.builder()
                .nom("kkkkk").prenom("llllll").build();
        return repo.findById(id).orElse(fakeClient);
    }

    @Override
    public List<Client> selectAllClients() {
        return repo.findAll();
    }

    @Override
    public void deleteClient(Client clt) {
        repo.delete(clt);

    }

    @Override
    public void deleteAllClient() {
        repo.deleteAll();

    }

    @Override
    public void deleteClientById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingClients() {
        return repo.count();
    }

    @Override
    public boolean verifyClientById(long id) {
        return repo.existsById(id);
    }

    // -----------------------Méthodes avec DTO-----------

    @Override
    public ClientResponse addClientWithDTO(ClientRequest clientRequest) {
        return mapper.fromEntityToDTO(repo.save(mapper.fromDTOToEntity(clientRequest)));
    }

    @Override
    public List<ClientResponse> saveClientsWithDTO(List<ClientRequest> clientRequests) {
        // Convertir DTOs en entités
        List<Client> clients = new ArrayList<>();
        for (ClientRequest req : clientRequests) {
            clients.add(mapper.fromDTOToEntity(req));
        }

        // Persister entités
        List<Client> clientsAdded = repo.saveAll(clients);

        // Convertir en DTOs de réponse
        List<ClientResponse> clientResponses = new ArrayList<>();
        for (Client client : clientsAdded) {
            clientResponses.add(mapper.fromEntityToDTO(client));
        }
        return clientResponses;
    }

    @Override
    public ClientResponse selectClientByIdWithGetWithDTO(long id) {
        Client client = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + id));
        return mapper.fromEntityToDTO(client);
    }

    @Override
    public ClientResponse selectClientByIdWithOrElseWithDTO(long id) {
        Client fakeClient = Client.builder()
                .nom("Client par défaut")
                .prenom("Prénom par défaut")
                .dateNaissance(LocalDate.now())
                .build();

        Client client = repo.findById(id).orElse(fakeClient);
        return mapper.fromEntityToDTO(client);
    }

    @Override
    public List<ClientResponse> selectAllClientsWithDTO() {
        List<Client> clients = repo.findAll();
        List<ClientResponse> clientResponses = new ArrayList<>();
        for (Client client : clients) {
            clientResponses.add(mapper.fromEntityToDTO(client));
        }
        return clientResponses;
    }

    @Override
    public void deleteClientWithDTO(ClientRequest clientRequest) {
        Client client = mapper.fromDTOToEntity(clientRequest);
        repo.delete(client);
    }

    @Override
    public Client addClientCc(Client clt) {
        return repo.save(clt);
    }


    @Override
    public void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client) {
        //Activer le cascade (Client)

        // Affecter l'adresse au client
        client.setAdresse(adresse);

        // Sauvegarder le client avec l'adresse affectée
        repo.save(client);
    }
}
