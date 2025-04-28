package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import bts.sio.api.model.Sport;
import bts.sio.api.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Athlète", description = "API de gestion des athlètes des Jeux Olympiques de Paris 2024")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Create - Add a new athlete
     * @param athlete An object athlete
     * @return The athlete object saved
     */
    @Operation(summary = "Créer un nouvel athlètes",
            description = "Ajoute un nouvel athlètes à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète créée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/athlete/ajouter")
    public Athlete createAthlete(
            @Parameter(description = "Athlète à créer")
            @RequestBody Athlete athlete
    ) {
        return athleteService.saveAthlete(athlete);
    }

    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @Operation(summary = "Obtenir un athlète",
            description = "Retourne un athlète avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @GetMapping("/athlete/consulter/{id}")
    public Athlete getAthlete(@PathVariable("id") final Long id) {
        Optional<Athlete> athlete = athleteService.getAthlete(id);
        return athlete.orElse(null);
    }

    /**
     * Read - Get all athletes
     * @return - An Iterable object of Athlete full filled
     */
    @Operation(summary = "Obtenir tous les athlètes",
            description = "Retourne la liste de tous les athlètes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste d'athlètes trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) })
    })
    @GetMapping("/athlete/lister")
    public Iterable<Athlete> getLesAthletes() {
        return athleteService.getLesAthletes();
    }

    /**
     * Read - Get all athletes from a country
     * @return - An Iterable object of Athlete full filled
     */
    @Operation(summary = "Obtenir les athlètes d'un pays",
            description = "Retourne tous les athlètes d'un pays donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des athlètes par pays trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) })
    })
    @GetMapping("/athlete/pays/{paysId}")
    public List<Athlete> getAthletes(@PathVariable Long paysId) {
        return athleteService.getLesAthletesByPays(paysId);
    }

    /**
     * Update - Update an existing athlete
     * @param id - The id of the athlete to update
     * @param athlete - The athlete object updated
     */
    @Operation(summary = "Mettre à jour un athlète",
            description = "Met à jour les informations d'un athlète existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @PutMapping("/athletes/modifier/{id}")
    public Athlete updateAthlete(@PathVariable("id") final Long id, @RequestBody Athlete athlete) {
        Optional<Athlete> ath = athleteService.getAthlete(id);
        if(ath.isPresent()) {
            Athlete currentAthlete = ath.get();

            String nom = athlete.getNom();
            if(nom != null) {
                currentAthlete.setNom(nom);
            }
            String prenom = athlete.getPrenom();
            if(prenom != null) {
                currentAthlete.setPrenom(prenom);
            }
            Pays pays = athlete.getPays();
            if(pays != null) {
                currentAthlete.setPays(pays);
            }
            Sport sport = athlete.getSport();
            if(sport != null) {
                currentAthlete.setSport(sport);
            }

            athleteService.saveAthlete(currentAthlete);
            return currentAthlete;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an athlete
     * @param id - The id of the athlete to delete
     */
    @Operation(summary = "Supprimer un athlète",
            description = "Supprime un athlète de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Athlète supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Athlète non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/athlete/supprimer/{id}")
    public void deleteAthlete(@PathVariable("id") final Long id) {
        athleteService.deleteAthlete(id);
    }

}
