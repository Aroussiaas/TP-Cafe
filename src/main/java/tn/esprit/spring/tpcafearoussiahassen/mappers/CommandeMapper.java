package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeRequest1;
import tn.esprit.spring.tpcafearoussiahassen.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Commande;


@Mapper(componentModel = "spring", uses = {DetailCommandeMapper.class,ClientMapper.class})

public interface CommandeMapper {
    Commande fromDTOToEntity(CommandeRequest commande);
    Commande fromDTOToEntity2(CommandeResponse commande);
    CommandeResponse fromEntityToDTO(Commande commande);
    CommandeRequest1 fromEntityToDTO2(Commande commandeRequest1);
}
