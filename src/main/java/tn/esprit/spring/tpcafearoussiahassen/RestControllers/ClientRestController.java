package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.ClientService;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("client")

public class ClientRestController {
    private final ClientService clientService;
    ClientService service;

//    @GetMapping
//    public List<Client> findAll() {
//        return service.selectAllClients();
//    }


    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PostMapping("/batch")
    public List<Client> saveClients(@RequestBody List<Client> clients) {
        return clientService.saveClient(clients);
    }

    // READ
    @GetMapping("/{id}/get")
    public Client getClientByIdWithGet(@PathVariable long id) {
        return clientService.selectClientByIdWithGet(id);
    }

    @GetMapping("/{id}/orElse")
    public Client getClientByIdWithOrElse(@PathVariable long id) {
        return clientService.selectClientByIdWithOrElse(id);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.selectAllClients();
    }

    @GetMapping("/count")
    public long countClients() {
        return clientService.countingClients();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyClientExists(@PathVariable long id) {
        return clientService.verifyClientById(id);
    }

    @DeleteMapping
    public void deleteClient(@RequestBody Client client) {
        clientService.deleteClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientService.deleteClientById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllClients() {
        clientService.deleteAllClient();
    }

    //------------------ DTOs -----------------------

    @PostMapping("/addClientWithDTO")
    public ClientResponse addClientWithDTO(@RequestBody ClientRequest clientRequest) {
        return clientService.addClientWithDTO(clientRequest);
    }

    @GetMapping("/getClientByIdWithDTO/{id}")
    public ClientResponse getClientByIdWithDTO(@PathVariable Long id) {
        return clientService.selectClientByIdWithGetWithDTO(id);
    }

    @PostMapping("saveClientsWithDTO")
    public List<ClientResponse> saveClientsWithDTO(List<ClientRequest> clientRequests) {
        return service.saveClientsWithDTO(clientRequests);
    }
    @GetMapping("/selectClientByIdWithGetWithDTO/{id}")
    public ClientResponse selectClientByIdWithGetWithDTO(long id) {
        return service.selectClientByIdWithGetWithDTO(id);
    }

    @GetMapping("/selectClientByIdWithOrElseWithDTO/{id}")
    public ClientResponse selectClientByIdWithOrElseWithDTO(long id) {
        return service.selectClientByIdWithOrElseWithDTO(id);
    }
    @GetMapping("/selectAllClientsWithDTO")
    public List<ClientResponse> selectAllClientsWithDTO() {
        return service.selectAllClientsWithDTO();
    }
    @DeleteMapping("/deleteClientWithDTO")
    public void deleteClientWithDTO(@RequestBody ClientRequest clientRequest) {
        service.deleteClientWithDTO(clientRequest);
    }

    @PostMapping("/addClientCc")
    public Client addClientCc(Client clt) {
        return service.addClientCc(clt);
    }

    @PostMapping("/ajouterEtAffecterAdresseAClient")
    public void ajouterEtAffecterAdresseAClient(@RequestBody Adresse adresse, @RequestBody Client client) {
        service.ajouterEtAffecterAdresseAClient(adresse, client);
    }


}
