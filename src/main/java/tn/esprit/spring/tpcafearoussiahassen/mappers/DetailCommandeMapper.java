package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;

@Mapper(componentModel = "spring", uses = {CommandeMapper.class,ArticleMapper.class})

public interface DetailCommandeMapper {
    DetailCommande fromDTOToEntity(DetailCommandeRequest detailCommande);
    DetailCommande fromDTOToEntity2(DetailCommandeResponse detailCommande);
    DetailCommandeResponse fromEntityToDTO(DetailCommande detailCommande);
    /////---------recupérer liste de detailCommandes par idCommande
    DetailCommandeRequest fromEntityToDTO2(DetailCommande detailCommande);

}
