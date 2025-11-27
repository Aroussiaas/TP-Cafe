package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.CarteFidelite;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;
import tn.esprit.spring.tpcafearoussiahassen.mappers.CarteFideliteMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.CarteFideliteRepository;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
//@Component
@AllArgsConstructor


public class CarteFideliteService implements ICarteFideliteService{

    CarteFideliteRepository repo;
    ClientRepository clientRepository;
    CarteFideliteMapper mapper;


    @Override
    public CarteFidelite addcarteFidelite(CarteFidelite cartfid) {
        return repo.save(cartfid);
    }

    @Override
    public List<CarteFidelite> savecarteFidelite(List<CarteFidelite> cartfids) {
        return repo.saveAll(cartfids);
    }

    @Override
    public CarteFidelite selectCarteFideliteByIdWithGet(long id) {
        return repo.findById(id).get();
    }

    @Override
    public CarteFidelite selectCarteFideliteByIdWithOrElse(long id) {
        CarteFidelite fakeCarte = CarteFidelite.builder()
                .pointsAccumules(789).build();
                return repo.findById(id).orElse(fakeCarte);
    }
    @Override
    public List<CarteFidelite> selectAllCarteFidelites() {
        return repo.findAll();
    }

    @Override
    public void deleteCarteFidelitee(CarteFidelite cartefid) {
        repo.delete(cartefid);

    }

    @Override
    public void deleteAllCarteFidelite() {
        repo.deleteAll();

    }

    @Override
    public void deleteCarteFideliteById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingCarteFidelites() {
        return repo.count();
    }

    @Override
    public boolean verifyCarteFideliteById(long id) {
        return repo.existsById(id);
    }

// -----------------------Méthodes avec DTO-----------

    @Override
    public CarteFideliteResponse addCarteFideliteWithDTO(CarteFideliteRequest carteFideliteRequest) {
        CarteFidelite carteFidelite = mapper.fromDTOToEntity(carteFideliteRequest);
        CarteFidelite carteSaved = repo.save(carteFidelite);
        return mapper.fromEntityToDTO(carteSaved);
    }

    @Override
    public List<CarteFideliteResponse> saveCartesFideliteWithDTO(List<CarteFideliteRequest> carteFideliteRequests) {
        // Convertir DTOs en entités
        List<CarteFidelite> cartesFidelite = new ArrayList<>();
        for (CarteFideliteRequest req : carteFideliteRequests) {
            cartesFidelite.add(mapper.fromDTOToEntity(req));
        }

        // Persister les entités
        List<CarteFidelite> cartesAdded = repo.saveAll(cartesFidelite);

        // Convertir les entités sauvegardées en DTOs de réponse
        List<CarteFideliteResponse> carteFideliteResponses = new ArrayList<>();
        for (CarteFidelite carte : cartesAdded) {
            carteFideliteResponses.add(mapper.fromEntityToDTO(carte));
        }
        return carteFideliteResponses;
    }

    @Override
    public CarteFideliteResponse selectCarteFideliteByIdWithGetWithDTO(long id) {
        CarteFidelite carteFidelite = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte Fidélité non trouvée avec l'ID: " + id));
        return mapper.fromEntityToDTO(carteFidelite);
    }

    @Override
    public CarteFideliteResponse selectCarteFideliteByIdWithOrElseWithDTO(long id) {
        CarteFidelite fakeCarte = CarteFidelite.builder()
                .pointsAccumules(0)
                .dateCreation(LocalDate.now())
                .dateCreation(LocalDate.now().plusYears(1))
                .build();

        CarteFidelite carteFidelite = repo.findById(id).orElse(fakeCarte);
        return mapper.fromEntityToDTO(carteFidelite);
    }

    @Override
    public List<CarteFideliteResponse> selectAllCartesFideliteWithDTO() {
        List<CarteFidelite> cartesFidelite = repo.findAll();
        List<CarteFideliteResponse> carteFideliteResponses = new ArrayList<>();
        for (CarteFidelite carte : cartesFidelite) {
            carteFideliteResponses.add(mapper.fromEntityToDTO(carte));
        }
        return carteFideliteResponses;
    }

    @Override
    public void deleteCarteFideliteWithDTO(CarteFideliteRequest carteFideliteRequest) {
        CarteFidelite carteFidelite = mapper.fromDTOToEntity(carteFideliteRequest);
        repo.delete(carteFidelite);
    }


    //------------------ affecterCarteToClient -----------------------

    @Override
    public void affecterCarteFideliteAClient(Long idCarteFidelite, Long idClient) {
        // findById carte fidelite(child)
        CarteFidelite carteFidelite = repo.findById(idCarteFidelite).get();
        //findById client (parent)
        Client client = clientRepository.findById(idClient).get();
        // set affecte child au parent ==>client.setCarteFidelite(carteFidelite)
        client.setCarteFidelite(carteFidelite);
        // persiste la modification clientRepository.save(client)
        clientRepository.save(client);

    }
    @Override
    public void ajouterClientEtCarteFidelite(CarteFidelite carte) {
        // Etape 1: Persister la carte de fidélité
        carte = repo.save(carte);
        Client c= clientRepository.save(carte.getClient());
        // Etape 2: Associer la carte de fidélité au client
        c.setCarteFidelite(carte);
        // Etape 3: Mettre à jour  le client avec la carte
        clientRepository.save(c);
    }

    public void addClientEtCarteFidelite(Client client) {
        //la creation de la carte de fidelite se fait dans le code
        CarteFidelite carteFidelite = CarteFidelite.builder()
                .pointsAccumules(0)
                .dateCreation(LocalDate.now())
                .build();
        client.setCarteFidelite(carteFidelite);
        clientRepository.save(client);
    }

}
