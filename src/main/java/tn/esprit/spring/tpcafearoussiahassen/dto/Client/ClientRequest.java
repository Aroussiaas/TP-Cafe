package tn.esprit.spring.tpcafearoussiahassen.dto.Client;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ClientRequest {
    String nom;
    String prenom;
    LocalDate dateNaissance;

}
