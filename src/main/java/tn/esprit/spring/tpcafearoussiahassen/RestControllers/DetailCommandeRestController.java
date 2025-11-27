package tn.esprit.spring.tpcafearoussiahassen.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.DetailCommandeService;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;


import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("detailCommande")

public class DetailCommandeRestController {

    private final DetailCommandeService detailCommandeService;


    @GetMapping
    public List<DetailCommande> getAllDetailCommandes() {
        return detailCommandeService.selectAllDetailCommandes();
    }

    //
    /*
    @GetMapping
    public List<DetailCommande> findAll() {
        return service.selectAllDetailCommandes();
    }
    */

    @PostMapping
    public DetailCommande addDetailCommande(@RequestBody DetailCommande detailCommande) {
        return detailCommandeService.addDetailCommande(detailCommande);
    }

    @PostMapping("/batch")
    public List<DetailCommande> saveDetailCommandes(@RequestBody List<DetailCommande> detailCommandes) {
        return detailCommandeService.saveDetailCommande(detailCommandes);
    }

    // READ
    @GetMapping("/{id}/get")
    public DetailCommande getDetailCommandeByIdWithGet(@PathVariable long id) {
        return detailCommandeService.selectDetailCommandeByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public DetailCommande getDetailCommandeByIdWithOrElse(@PathVariable long id) {
        return detailCommandeService.selectDetailCommandeByIdWithOrElse(id);
    }

    @GetMapping("/count")
    public long countDetailCommandes() {
        return detailCommandeService.countingDetailCommandes();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyDetailCommandeExists(@PathVariable long id) {
        return detailCommandeService.verifyDetailCommandeById(id);
    }

    // DELETE - CORRIGÉ
    @DeleteMapping("/{id}")
    public void deleteDetailCommandeById(@PathVariable long id) {
        detailCommandeService.deleteDetailCommandeById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllDetailCommandes() {
        detailCommandeService.deleteAllDetailCommande();
    }

    //------------------ DTOs -----------------------
    @PostMapping("/addDetailCommandeWithDTO")
    public DetailCommandeResponse addDetailCommandeWithDTO(@RequestBody DetailCommandeRequest request) {
        return detailCommandeService.addDetailCommandeWithDTO(request);
    }

    @PostMapping("/saveDetailCommandesWithDTO") // Corrigé le mapping
    public List<DetailCommandeResponse> saveDetailCommandesWithDTO(@RequestBody List<DetailCommandeRequest> requests) {
        return detailCommandeService.saveDetailCommandesWithDTO(requests);
    }

    @GetMapping("/selectDetailCommandeByIdWithDTO/{id}") // Changé de POST à GET
    public DetailCommandeResponse selectDetailCommandeByIdWithDTO(@PathVariable long id) {
        return detailCommandeService.selectDetailCommandeByIdWithDTO(id);
    }

    @GetMapping("/selectAllDetailCommandesWithDTO")
    public List<DetailCommandeResponse> selectAllDetailCommandesWithDTO() {
        return detailCommandeService.selectAllDetailCommandesWithDTO();
    }

    @DeleteMapping("/deleteDetailCommandeWithDTO")
    public void deleteDetailCommandeWithDTO(@RequestBody DetailCommandeRequest request) {
        detailCommandeService.deleteDetailCommandeWithDTO(request);
    }

}
