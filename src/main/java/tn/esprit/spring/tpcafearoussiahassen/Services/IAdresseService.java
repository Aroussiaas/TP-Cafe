package tn.esprit.spring.tpcafearoussiahassen.Services;

import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;

import java.util.List;

public interface IAdresseService {
    Adresse addAdresse(Adresse a);
    AdresseResponse addAdresseWithDTO(AdresseRequest adresseRequest);
    //-----------------------------------------------------------------
    List<Adresse> saveAdresses(List<Adresse> adresses);
    List<AdresseResponse> saveAdressesWithDTO(List<AdresseRequest> adresses);

    //-------------------------------------------------------------------
    Adresse selectAdresseByIdWithGet(long id);
    AdresseResponse selectAdresseByIdWithGetWithDTO(long id);

    //------------------------------------------------------------------
    Adresse selectAdresseByIdWithOrElse(long id);

    //--------------------------------------------------------------
    List<Adresse> selectAllAdresses();

    //-----------------------------------------------------
    void deleteAdresse(Adresse a);
    void deleteAdresseWithDTO(AdresseRequest adresseRequest);
   //-----------------------------------------------------------

    void deleteAllAdresse();
    void deleteAdresseById(long id);
    long countingAdresses();
    boolean verifyAdresseById(long id);

    ///------------------affectation adresse to client----------------//
    void affecterAdresseAClient(String rue, Long idClient);

    void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client);
}
