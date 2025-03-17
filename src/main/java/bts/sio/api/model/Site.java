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

    @Column(name="nom")
    private String nom;

    @Column(name="rue")
    private String rue;

    @Column(name="code_postal")
    private Integer code_postal;

    @Column(name="ville")
    private String ville;

}