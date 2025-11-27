package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;


@Mapper(componentModel = "spring", uses = {AdresseMapper.class, CarteFideliteMapper.class,CommandeMapper.class})

public interface ClientMapper {
    Client fromDTOToEntity(ClientRequest client);
    Client fromDTOToEntity2(ClientResponse client);
    ClientResponse fromEntityToDTO(Client client);
}
