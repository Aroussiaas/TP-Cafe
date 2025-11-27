package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;
import tn.esprit.spring.tpcafearoussiahassen.mappers.DetailCommandeMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.DetailCommandeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Component
@AllArgsConstructor

public class    DetailCommandeService implements IDetailCommandeService{

    DetailCommandeRepository repo;
    DetailCommandeMapper mapper;

    @Override
    public DetailCommande addDetailCommande(DetailCommande dtl) {
        return repo.save(dtl);
    }

    @Override
    public List<DetailCommande> saveDetailCommande(List<DetailCommande> DetailCommandes) {
        return repo.saveAll(DetailCommandes);
    }

    @Override
    public DetailCommande selectDetailCommandeByIdWithGet(long id) {
        return repo.findById(id).get();
    }

    @Override
    public DetailCommande selectDetailCommandeByIdWithOrElse(long id) {
        DetailCommande fakeDetailCommande = DetailCommande.builder()
                .sousTotalDetailArticle(5875)
                .quantiteArticle(895)
                .sousTotalDetailArticleApresPromo(587)
                .sousTotalDetailArticle(587).build();
        return repo.findById(id).orElse(fakeDetailCommande);

    }

    @Override
    public List<DetailCommande> selectAllDetailCommandes() {
        return repo.findAll();
    }

    @Override
    public void deleteDetailCommande(DetailCommande dtl) {
        repo.delete(dtl);

    }

    @Override
    public void deleteAllDetailCommande() {
        repo.deleteAll();

    }

    @Override
    public void deleteDetailCommandeById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingDetailCommandes() {
        return repo.count();
    }

    @Override
    public boolean verifyDetailCommandeById(long id) {
        return repo.existsById(id);
    }

    // -----------------------Méthodes avec DTO-----------

    @Override
    public DetailCommandeResponse addDetailCommandeWithDTO(DetailCommandeRequest request) {
        DetailCommande detailCommande = mapper.fromDTOToEntity(request);
        DetailCommande savedDetailCommande = repo.save(detailCommande);
        return mapper.fromEntityToDTO(savedDetailCommande);
    }

    @Override
    public List<DetailCommandeResponse> saveDetailCommandesWithDTO(List<DetailCommandeRequest> requests) {
        List<DetailCommande> detailCommandes = requests.stream()
                .map(mapper::fromDTOToEntity)
                .collect(Collectors.toList());

        List<DetailCommande> savedDetailCommandes = repo.saveAll(detailCommandes);

        return savedDetailCommandes.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DetailCommandeResponse selectDetailCommandeByIdWithDTO(long id) {
        DetailCommande detailCommande = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailCommande non trouvé avec l'ID: " + id));
        return mapper.fromEntityToDTO(detailCommande);
    }

    @Override
    public List<DetailCommandeResponse> selectAllDetailCommandesWithDTO() {
        List<DetailCommande> detailCommandes = repo.findAll();
        return detailCommandes.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetailCommandeWithDTO(DetailCommandeRequest request) {
        DetailCommande detailCommande = mapper.fromDTOToEntity(request);
        repo.delete(detailCommande);
    }
}
