package tn.esprit.spring.tpcafearoussiahassen.Services;


import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.CarteFidelite;

import java.util.List;

public interface ICarteFideliteService {
    CarteFidelite addcarteFidelite(CarteFidelite cartfid);
    List<CarteFidelite> savecarteFidelite(List<CarteFidelite> cartfids);
    CarteFidelite selectCarteFideliteByIdWithGet(long id);
    CarteFidelite selectCarteFideliteByIdWithOrElse(long id);
    List<CarteFidelite> selectAllCarteFidelites();
    void deleteCarteFidelitee(CarteFidelite cartefids);
    void deleteAllCarteFidelite();
    void deleteCarteFideliteById(long id);
    long countingCarteFidelites();
    boolean verifyCarteFideliteById(long id);
    // Méthodes avec DTO (Nouvelles)
    CarteFideliteResponse addCarteFideliteWithDTO(CarteFideliteRequest carteFideliteRequest);
    List<CarteFideliteResponse> saveCartesFideliteWithDTO(List<CarteFideliteRequest> carteFideliteRequests);
    CarteFideliteResponse selectCarteFideliteByIdWithGetWithDTO(long id);
    CarteFideliteResponse selectCarteFideliteByIdWithOrElseWithDTO(long id);
    List<CarteFideliteResponse> selectAllCartesFideliteWithDTO();
    void deleteCarteFideliteWithDTO(CarteFideliteRequest carteFideliteRequest);
    //----------------affectation carte fidelite to client----------------//
    void affecterCarteFideliteAClient(Long idCarteFidelite, Long idClient);

    void ajouterClientEtCarteFidelite(CarteFidelite carte);
}
