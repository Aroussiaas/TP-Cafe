package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.CommandeService;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest1;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Commande;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("commande")

public class CommandeRestController {

    private final CommandeService commandeService;



    @PostMapping
    public Commande addCommande(@RequestBody Commande commande) {
        return commandeService.addCommande(commande);
    }

    @PostMapping("/batch")
    public List<Commande> saveCommandes(@RequestBody List<Commande> commandes) {
        return commandeService.saveCommande(commandes);
    }

    // READ
    @GetMapping("/{id}/get")
    public Commande getCommandeByIdWithGet(@PathVariable long id) {
        return commandeService.selectCommandeByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public Commande getCommandeByIdWithOrElse(@PathVariable long id) {
        return commandeService.selectCommandeByIdWithOrElse(id);
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.selectAllCommandes();
    }

    @GetMapping("/count")
    public long countCommandes() {
        return commandeService.countingCommandes();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyCommandeExists(@PathVariable long id) {
        return commandeService.verifyCommandeById(id);
    }

    // DELETE - CORRIGÉ
    @DeleteMapping("/{id}")
    public void deleteCommandeById(@PathVariable long id) {
        commandeService.deleteCommandeById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCommandes() {
        commandeService.deleteAllCommande();
    }

    //------------------ DTOs -----------------------
    @PostMapping("/addCommandeWithDTO")
    public CommandeResponse addCommandeWithDTO(@RequestBody CommandeRequest commandeRequest) { // Ajout @RequestBody
        return commandeService.addCommandeWithDTO(commandeRequest);
    }

    @PostMapping("/saveCommandesWithDTO") // Changé de GET à POST
    public List<CommandeResponse> saveCommandesWithDTO(@RequestBody List<CommandeRequest> commandeRequests) { // Ajout @RequestBody
        return commandeService.saveCommandesWithDTO(commandeRequests);
    }

    @GetMapping("/selectCommandeByIdWithGetWithDTO/{id}")
    public CommandeResponse selectCommandeByIdWithGetWithDTO(@PathVariable long id) {
        return commandeService.selectCommandeByIdWithGetWithDTO(id);
    }

    @GetMapping("/selectAllCommandesWithDTO")
    public List<CommandeResponse> selectAllCommandesWithDTO() {
        return commandeService.selectAllCommandesWithDTO();
    }

    @DeleteMapping("/deleteCommandeWithDTO")
    public void deleteCommandeWithDTO(@RequestBody CommandeRequest commandeRequest) { // Ajout @RequestBody
        commandeService.deleteCommandeWithDTO(commandeRequest);
    }

    @GetMapping("/recupererCommandeParIdWithDTO2/{idCommande}")
    public CommandeRequest1 recupererCommandeParIdWithDTO2(@PathVariable Long idCommande) { // Long au lieu de long
        return commandeService.recupererCommandeParIdWithDTO2(idCommande);
    }

    @PutMapping("/affecterCommandeAClient/{idCommande}/{idClient}") // Changé de GET à PUT
    public void affecterCommandeAClient(@PathVariable Long idCommande, @PathVariable Long idClient) {
        commandeService.affecterCommandeAClient(idCommande, idClient);
    }

    @PutMapping("/desaffecterCommandeClient/{idCommande}") // Changé de DELETE à PUT
    public void desaffecterCommandeClient(@PathVariable Long idCommande) { // Ajout @PathVariable
        commandeService.desaffecterCommandeClient(idCommande);
    }
}
