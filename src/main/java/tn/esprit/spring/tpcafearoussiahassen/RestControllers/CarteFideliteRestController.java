package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.CarteFideliteService;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.CarteFidelite;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("carteFidelite")

public class CarteFideliteRestController {

    CarteFideliteService service;

    @GetMapping
    public List<CarteFidelite> getAllCartesFidelite() {
        return service.selectAllCarteFidelites();
    }

    @PostMapping
    public CarteFidelite addCarteFidelite(@RequestBody CarteFidelite carteFidelite) {
        return service.addcarteFidelite(carteFidelite);
    }

    @PostMapping("/batch")
    public List<CarteFidelite> saveCartesFidelite(@RequestBody List<CarteFidelite> cartesFidelite) {
        return service.savecarteFidelite(cartesFidelite);
    }

    @GetMapping("/{id}")
    public CarteFidelite getCarteById(@PathVariable long id) {
        return service.selectCarteFideliteByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public CarteFidelite getCarteByIdOrElse(@PathVariable long id) {
        return service.selectCarteFideliteByIdWithOrElse(id);
    }

    @GetMapping("/count")
    public long countCartes() {
        return service.countingCarteFidelites();
    }

    @GetMapping("/{id}/exists")
    public boolean exists(@PathVariable long id) {
        return service.verifyCarteFideliteById(id);
    }

    @DeleteMapping
    public void deleteCarte(@RequestBody CarteFidelite carteFidelite) {
        service.deleteCarteFidelitee(carteFidelite);
    }

    @DeleteMapping("/{id}")
    public void deleteCarteById(@PathVariable long id) {
        service.deleteCarteFideliteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCartes() {
        service.deleteAllCarteFidelite();
    }

    // ---------------- DTO ----------------

    @PostMapping("/addCarteWithDTO")
    public CarteFideliteResponse addCarteWithDTO(@RequestBody CarteFideliteRequest request) {
        return service.addCarteFideliteWithDTO(request);
    }

    @PostMapping("/saveCartesWithDTO/batch")
    public List<CarteFideliteResponse> saveCartesWithDTO(@RequestBody List<CarteFideliteRequest> requests) {
        return service.saveCartesFideliteWithDTO(requests);
    }

    @GetMapping("/getCarteByIdWithDTO/{id}")
    public CarteFideliteResponse getCarteByIdWithDTO(@PathVariable Long id) {
        return service.selectCarteFideliteByIdWithGetWithDTO(id);
    }

    @GetMapping("/getAllCartesWithDTO")
    public List<CarteFideliteResponse> getAllCartesWithDTO() {
        return service.selectAllCartesFideliteWithDTO();
    }

    @DeleteMapping("/deleteCarteWithDTO")
    public void deleteCarteWithDTO(@RequestBody CarteFideliteRequest request) {
        service.deleteCarteFideliteWithDTO(request);
    }

    // ---------------- AFFECTATION ----------------
    @PostMapping("/affecterCarteAClient/{idCarte}/client/{idClient}")
    public void affecterCarteAClient(@PathVariable Long idCarte,
                                     @PathVariable Long idClient) {
        service.affecterCarteFideliteAClient(idCarte, idClient);
    }

    @PostMapping("/ajouterClientEtCarteFidelite")
    public void ajouterClientEtCarteFidelite(CarteFidelite carte) {
        service.ajouterClientEtCarteFidelite(carte);
    }


}
