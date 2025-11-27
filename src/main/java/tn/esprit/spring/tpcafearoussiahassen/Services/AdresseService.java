package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;
import tn.esprit.spring.tpcafearoussiahassen.mappers.AdresseMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.AdresseRepository;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
//@Component
@AllArgsConstructor

public class AdresseService implements IAdresseService{

    AdresseRepository repo;
    AdresseMapper mapper;
    ClientRepository clientRepository;

    @Override
    public Adresse addAdresse(Adresse a) {
       return repo.save(a);

    }
    @Override
    public Adresse selectAdresseByIdWithGet(long id) {
        return repo.findById(id).get();
    }


    @Override
    public Adresse selectAdresseByIdWithOrElse(long id) {
        Adresse fakeAdresse = Adresse.builder().rue("korniche")
                .ville("gabes")
                .codePostal(895623).build();
        return  repo.findById(id).orElse(fakeAdresse);
      }

    @Override
    public List<Adresse> selectAllAdresses() {
        return repo.findAll();
    }

    @Override
    public void deleteAdresse(Adresse a) {
        repo.delete(a);

    }


    @Override
    public void deleteAllAdresse() {
        repo.deleteAll();

    }

    @Override
    public void deleteAdresseById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingAdresses() {
        return repo.count();
    }

    @Override
    public boolean verifyAdresseById(long id) {
        return repo.existsById(id);
    }


    //------------------ DTO -----------------------

    @Override
    public void deleteAdresseWithDTO(AdresseRequest adresseRequest) {
        Adresse adresse = mapper.fromDTOToEntity(adresseRequest);
        repo.delete(adresse);
    }

    @Override
    public AdresseResponse addAdresseWithDTO(AdresseRequest adresseRequest) {
        return mapper.fromEntityToDTO(repo.save(mapper.fromDTOToEntity(adresseRequest)));
    }

    @Override
    public List<Adresse> saveAdresses(List<Adresse> adresses) {
        return repo.saveAll(adresses);
    }


    @Override
    public List<AdresseResponse> saveAdressesWithDTO(List<AdresseRequest> adresseRequests) {
        // convert DTOs to entities
        List<Adresse> adresses = new ArrayList<>();
        for (AdresseRequest req : adresseRequests) {
            adresses.add(mapper.fromDTOToEntity(req));
        }

        // persist entities
        List<Adresse> adresseAdded = repo.saveAll(adresses);

        // convert saved entities to response DTOs
        List<AdresseResponse> adresseResponses = new ArrayList<>();
        for (Adresse adresse : adresseAdded) {
            adresseResponses.add(mapper.fromEntityToDTO(adresse));
        }
        return adresseResponses;
    }


    @Override
    public AdresseResponse selectAdresseByIdWithGetWithDTO(long id) {
        Adresse adresse = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée avec l'ID: " + id));
        return mapper.fromEntityToDTO(adresse);
    }

    //------------------ affecterAdresseToClient -----------------------

    @Override
    public void affecterAdresseAClient(String rue, Long idClient) {
        //findByRue (keyword)--> Adresse (child)
        Adresse adresse = repo.findByRue(rue);
        // recupByCin(JPQL --> Client(parent)
        Client client = clientRepository.findById(idClient).orElseThrow(()->new RuntimeException("Client not found"));
        //Appliquer la regle : on affecte le child au parent ==>client.setAdresse(adresse)
        client.setAdresse(adresse);
        //persister les modifs -> parentRepository.save(parent)
        clientRepository.save(client);

    }
    @Override
    public void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client) {
        //persist adresse
        Adresse adresseAdded = repo.save(adresse);
        //affecter adresse au client
        client.setAdresse(adresseAdded);
        //persister client
        clientRepository.save(client);

    }


}
