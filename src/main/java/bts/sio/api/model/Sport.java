package bts.sio.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sport")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nom")
    private String nom;

    @Column(name="descriptif")
    private String descriptif;

    @ManyToMany
    @JoinTable( name = "site_sport",
            joinColumns = @JoinColumn( name = "sport_id" ),
            inverseJoinColumns = @JoinColumn( name = "site_id" ) )
    private List<Site> site = new ArrayList<>();
}
