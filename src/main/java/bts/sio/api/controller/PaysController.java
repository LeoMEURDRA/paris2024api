package bts.sio.api.controller;

import bts.sio.api.model.Pays;
import bts.sio.api.service.PaysService;
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
@Tag(name = "Pays", description = "API de gestion des pays")
public class PaysController {

    @Autowired
    private PaysService paysService;

    /**
     * Create - Add a new pays
     * @param pays An object pays
     * @return The pays object saved
     */
    @Operation(summary = "Créer un nouveau pays",
            description = "Ajoute un nouveau pays à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pays créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pays.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/pays/ajouter")
    public Pays createPays(@RequestBody Pays pays) {
        return paysService.savePays(pays);
    }

    /**
     * Read - Get one pays
     * @param id The id of the pays
     * @return An Pays object full filled
     */
    @Operation(summary = "Obtenir un pays",
            description = "Retourne un pays avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pays trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pays.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Pays non trouvé",
                    content = @Content)
    })
    @GetMapping("/pays/consulter/{id}")
    public Pays getPays(@PathVariable("id") final Long id) {
        Optional<Pays> pays = paysService.getPays(id);
        return pays.orElse(null);
    }

    /**
     * Read - Get all pays
     * @return - An Iterable object of Pays full filled
     */
    @Operation(summary = "Obtenir tous les pays",
            description = "Retourne la liste de tous les pays")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des pays trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pays.class)) })
    })
    @GetMapping("/pays/lister")
    public Iterable<Pays> getLesPays() {
        return paysService.getLesPays();
    }

    /**
     * Update - Update an existing pays
     * @param id - The id of the pays to update
     * @param pays - The pays object updated
     */
    @Operation(summary = "Mettre à jour un pays",
            description = "Met à jour les informations d'un pays existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pays mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pays.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Pays non trouvé",
                    content = @Content)
    })
    @PutMapping("/pays/modifier/{id}")
    public Pays updatePays(@PathVariable("id") final Long id, @RequestBody Pays pays) {
        Optional<Pays> oly = paysService.getPays(id);
        if(oly.isPresent()) {
            Pays currentPays = oly.get();

            String nom = pays.getNom();
            if(nom != null) {
                currentPays.setNom(nom);
            }
            String code = pays.getCode();
            if(code != null) {
                currentPays.setCode(code);
            }

            paysService.savePays(currentPays);
            return currentPays;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a pays
     * @param id - The id of the pays to delete
     */
    @Operation(summary = "Supprimer un pays",
            description = "Supprime un pays de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pays supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Pays non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/pays/supprimer/{id}")
    public void deletePays(@PathVariable("id") final Long id) {
        paysService.deletePays(id);
    }
}
