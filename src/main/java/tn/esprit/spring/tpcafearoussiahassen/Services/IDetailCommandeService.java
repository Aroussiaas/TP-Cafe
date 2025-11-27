package tn.esprit.spring.tpcafearoussiahassen.Services;


import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;

import java.util.List;

public interface IDetailCommandeService {
    DetailCommande addDetailCommande(DetailCommande dtl);
    List<DetailCommande> saveDetailCommande(List<DetailCommande> DetailCommandes);
    DetailCommande selectDetailCommandeByIdWithGet(long id);
    DetailCommande selectDetailCommandeByIdWithOrElse(long id);
    List<DetailCommande> selectAllDetailCommandes();
    void deleteDetailCommande(DetailCommande dtl);
    void deleteAllDetailCommande();
    void deleteDetailCommandeById(long id);
    long countingDetailCommandes();
    boolean verifyDetailCommandeById(long id);

    //-----------------------méthodes avec DTO----------------------------------
    DetailCommandeResponse addDetailCommandeWithDTO(DetailCommandeRequest request);
    List<DetailCommandeResponse> saveDetailCommandesWithDTO(List<DetailCommandeRequest> requests);
    DetailCommandeResponse selectDetailCommandeByIdWithDTO(long id);
    List<DetailCommandeResponse> selectAllDetailCommandesWithDTO();
    void deleteDetailCommandeWithDTO(DetailCommandeRequest request);
}

