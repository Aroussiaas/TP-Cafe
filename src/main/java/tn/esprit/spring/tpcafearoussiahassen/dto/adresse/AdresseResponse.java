package tn.esprit.spring.tpcafearoussiahassen.dto.adresse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AdresseResponse {
    Long id;
    String rue;
    String ville;
    int codePostal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
