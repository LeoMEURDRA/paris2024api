package bts.sio.api.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="duree")
    private Integer duree;

    @Column(name="descriptif")
    private String descriptif;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypePromotion type;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "sport_id")
    private Sport sport;

}