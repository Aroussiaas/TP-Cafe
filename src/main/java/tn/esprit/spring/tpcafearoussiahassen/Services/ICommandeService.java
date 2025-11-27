package tn.esprit.spring.tpcafearoussiahassen.Services;


import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest1;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Commande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeService {
    Commande addCommande(Commande cmd);
    List<Commande> saveCommande(List<Commande> commandes);
    Commande selectCommandeByIdWithGet(long id);
    Commande selectCommandeByIdWithOrElse(long id);
    List<Commande> selectAllCommandes();
    void deleteCommande(Commande cmd);
    void deleteAllCommande();
    void deleteCommandeById(long id);
    long countingCommandes();
    boolean verifyCommandeById(long id);

    //---------------méthodes avec DTO
    CommandeResponse addCommandeWithDTO(CommandeRequest commandeRequest);
    List<CommandeResponse> saveCommandesWithDTO(List<CommandeRequest> commandeRequests);
    CommandeResponse selectCommandeByIdWithGetWithDTO(long id);
    List<CommandeResponse> selectAllCommandesWithDTO();
    void deleteCommandeWithDTO(CommandeRequest commandeRequest);
    //------------------recupérer detailCommande par idCommande
    CommandeRequest1 recupererCommandeParIdWithDTO2 (Long idCommande);
    void affecterCommandeAClient(Long idCommande, Long idClient);
    void affecterCommandeAClient2(LocalDate dateCommande, String nomClient, String prenomClient);
    void desaffecterCommandeClient(Long idCommande);

}
