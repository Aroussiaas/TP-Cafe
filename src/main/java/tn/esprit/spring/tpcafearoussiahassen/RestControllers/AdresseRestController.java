package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.AdresseService;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("adresse")

public class AdresseRestController {
    private final AdresseService adresseService;


    @PostMapping("/add")
    public Adresse addAdresse(@RequestBody Adresse adresse) {
        return adresseService.addAdresse(adresse);
    }

    @PostMapping("/batch")
    public List<Adresse> saveAdresses(@RequestBody List<Adresse> adresses) {
        return adresseService.saveAdresses(adresses);
    }


    @GetMapping("/{id}/get")
    public Adresse getAdresseByIdWithGet(@PathVariable long id) {
        return adresseService.selectAdresseByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public Adresse getAdresseByIdWithOrElse(@PathVariable long id) {
        return adresseService.selectAdresseByIdWithOrElse(id);
    }

    @GetMapping("/all")
    public List<Adresse> getAllAdresses() {
        return adresseService.selectAllAdresses();
    }

    @GetMapping("/count")
    public long countAdresses() {
        return adresseService.countingAdresses();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyAdresseExists(@PathVariable long id) {
        return adresseService.verifyAdresseById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAdresse(@RequestBody Adresse adresse) {
        adresseService.deleteAdresse(adresse);
    }

    @DeleteMapping("/{id}")
    public void deleteAdresseById(@PathVariable long id) {
        adresseService.deleteAdresseById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllAdresses() {
        adresseService.deleteAllAdresse();
    }
    //------------------ DTOs -----------------------

    @GetMapping("/getAdresseByIdWithDTO/{id}")
    public AdresseResponse getAdresseByIdWithDTO(@PathVariable Long id) {
        return adresseService.selectAdresseByIdWithGetWithDTO(id);
    }
    @PostMapping("/saveAdressesWithDTO")
    public List<AdresseResponse> saveAdressesWithDTO(List<AdresseRequest> adresseRequests) {
        return adresseService.saveAdressesWithDTO(adresseRequests);
    }

    @PostMapping("/addAdresseWithDTO")
    public AdresseResponse addAdresseWithDTO(AdresseRequest adresseRequest){
        return adresseService.addAdresseWithDTO(adresseRequest);
    }

    @DeleteMapping("/deleteAdresseWithDTO")
    public void deleteAdresseWithDTO(AdresseRequest adresseRequest) {
        adresseService.deleteAdresseWithDTO(adresseRequest);
    }

    @PostMapping("/affecterAdresseAClient/{rue}/{idClient}")
    public void affecterAdresseAClient(String rue, Long idClient) {
        adresseService.affecterAdresseAClient(rue, idClient);
    }


}
