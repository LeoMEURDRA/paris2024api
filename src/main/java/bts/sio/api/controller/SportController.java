package bts.sio.api.controller;

import bts.sio.api.model.Sport;
import bts.sio.api.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@Tag(name = "Sport", description = "API de gestion des sports")
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * Create - Add a new sport
     * @param sport An object sport
     * @return The sport object saved
     */
    @Operation(summary = "Créer un nouveau sport",
            description = "Ajoute un nouveau sport à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/sport/ajouter")
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }

    /**
     * Read - Get one sport
     * @param id The id of the sport
     * @return An sport object full filled
     */
    @Operation(summary = "Obtenir un sport",
            description = "Retourne un sport avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @GetMapping("/sport/consulter/{id}")
    public Sport getSport(@PathVariable("id") final Long id) {
        Optional<Sport> sport = sportService.getSport(id);
        return sport.orElse(null);
    }

    /**
     * Read - Get all sports
     * @return - An Iterable object of sport full filled
     */
    @Operation(summary = "Obtenir tous les sports",
            description = "Retourne la liste de tous les sports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des sports trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) })
    })
    @GetMapping("/sport/lister")
    public Iterable<Sport> getLesSports() {
        return sportService.getLesSports();
    }

    /**
     * Update - Update an existing sport
     * @param id - The id of the sport to update
     * @param sport - The sport object updated
     */
    @Operation(summary = "Mettre à jour un sport",
            description = "Met à jour les informations d'un sport existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sport.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @PutMapping("/sport/modifier/{id}")
    public Sport updateSport(@PathVariable("id") final Long id, @RequestBody Sport sport) {
        Optional<Sport> e = sportService.getSport(id);
        if (e.isPresent()) {
            Sport currentSport = e.get();

            String nom = sport.getNom();
            if (nom != null) {
                currentSport.setNom(nom);
            }
            String descriptif = sport.getDescriptif();
            if (descriptif != null) {
                currentSport.setDescriptif(descriptif);
            }

            sportService.saveSport(currentSport);
            return currentSport;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an sport
     * @param id - The id of the sport to delete
     */
    @Operation(summary = "Supprimer un sport",
            description = "Supprime un sport de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Sport supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Sport non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/sport/supprimer/{id}")
    public void deleteSport(@PathVariable("id") final Long id) {
        sportService.deleteSport(id);
    }

}
