package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest1;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;
import tn.esprit.spring.tpcafearoussiahassen.entities.Commande;
import tn.esprit.spring.tpcafearoussiahassen.entities.StatusCommande;
import tn.esprit.spring.tpcafearoussiahassen.mappers.CommandeMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ClientRepository;
import tn.esprit.spring.tpcafearoussiahassen.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
//@Component
@AllArgsConstructor

public class CommandeService implements ICommandeService{
    private final CommandeRepository commandeRepository;
    CommandeRepository repo;
    CommandeMapper mapper;
    ClientRepository clientRepository;



    @Override
    public Commande addCommande(Commande cmd) {
        return repo.save(cmd);
    }

    @Override
    public List<Commande> saveCommande(List<Commande> commandes) {
        return repo.saveAll(commandes);
    }

    @Override
    public Commande selectCommandeByIdWithGet(long id) {
        return repo.findById(id).get();
    }

    @Override
    public Commande selectCommandeByIdWithOrElse(long id) {
        Commande fakeCommande = Commande.builder().totalCommande(500000)
                .statusCommande(StatusCommande.EN_COURS)
                .build();
        return repo.findById(id).orElse(fakeCommande);
    }

    @Override
    public List<Commande> selectAllCommandes() {
        return repo.findAll();
    }

    @Override
    public void deleteCommande(Commande cmd) {
        repo.delete(cmd);

    }

    @Override
    public void deleteAllCommande() {
        repo.deleteAll();

    }

    @Override
    public void deleteCommandeById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingCommandes() {
        return repo.count();
    }

    @Override
    public boolean verifyCommandeById(long id) {
        return repo.existsById(id);
    }

    // -----------------------Méthodes avec DTO-----------

    @Override
    public CommandeResponse addCommandeWithDTO(CommandeRequest commandeRequest) {
        Commande commande = mapper.fromDTOToEntity(commandeRequest);
        Commande savedCommande = repo.save(commande);
        return mapper.fromEntityToDTO(savedCommande);
    }

    @Override
    public List<CommandeResponse> saveCommandesWithDTO(List<CommandeRequest> commandeRequests) {
        // Convertir DTOs en entités
        List<Commande> commandes = commandeRequests.stream()
                .map(mapper::fromDTOToEntity)
                .collect(Collectors.toList());

        // Persister les entités
        List<Commande> commandesAdded = repo.saveAll(commandes);

        // Convertir les entités sauvegardées en DTOs de réponse
        return commandesAdded.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeResponse selectCommandeByIdWithGetWithDTO(long id) {
        Commande commande = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + id));
        return mapper.fromEntityToDTO(commande);
    }

    @Override
    public List<CommandeResponse> selectAllCommandesWithDTO() {
        List<Commande> commandes = repo.findAll();
        return commandes.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommandeWithDTO(CommandeRequest commandeRequest) {
        Commande commande = mapper.fromDTOToEntity(commandeRequest);
        repo.delete(commande);
    }

    //-----------RecupérationDetailCommande----------

    @Override
    public CommandeRequest1 recupererCommandeParIdWithDTO2(Long idCommande) {
        Commande c = repo.findById(idCommande).get();
        return mapper.fromEntityToDTO2(c);
    }

    @Override
    public void affecterCommandeAClient(Long idCommande, Long idClient) {
        Commande commande = repo.findById(idCommande).get();
        Client client = clientRepository.findById(idClient).get();
        commande.setClient(client);
        repo.save(commande);

    }

    @Override
    public void affecterCommandeAClient2(LocalDate dateCommande, String nomClient, String prenomClient) {
        Client client = clientRepository.findByNomAndPrenom(nomClient, prenomClient);
        Commande commande = Commande.builder()
                .dateCommande(dateCommande)
                .client(client)
                .build();
        repo.save(commande);

    }

    @Override
    public void desaffecterCommandeClient(Long idCommande) {
        Commande commande = repo.findById(idCommande).get();
        commande.setClient(null);///on a pas besoin de faire appel au clientRepository car on ne modifie pas l'entité client
        repo.save(commande);
    }

    public void ajouterCommandeEtAffecterAClient(String nomClient, String Prenom, Commande c) {
        Client client = clientRepository.findByNomAndPrenom(nomClient, Prenom);
        Commande commande = addCommande(c);
        c.setClient(client);
        repo.save(c);
    }





}
