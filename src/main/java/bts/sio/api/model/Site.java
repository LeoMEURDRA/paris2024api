package bts.sio.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom;
    private String rue;
    private Integer code_postal;
    private String ville;

}
