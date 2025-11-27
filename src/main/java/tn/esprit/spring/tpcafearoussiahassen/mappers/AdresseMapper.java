package tn.esprit.spring.tpcafearoussiahassen.mappers;
import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.adresse.AdresseResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})

public interface AdresseMapper {
    Adresse fromDTOToEntity(AdresseRequest adresse);
    Adresse fromDTOToEntity2(AdresseResponse adresse);
    AdresseResponse fromEntityToDTO(Adresse adresse);



}
