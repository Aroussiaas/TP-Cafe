package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.CarteFidelite;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})

public interface CarteFideliteMapper {
    CarteFidelite fromDTOToEntity(CarteFideliteRequest carteFidelite);
    CarteFidelite fromDTOToEntity2(CarteFideliteResponse carteFidelite);
    CarteFideliteResponse fromEntityToDTO(CarteFidelite carteFidelite);

}
