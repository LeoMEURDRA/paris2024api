package bts.sio.api.controller;

import bts.sio.api.model.Olympiade;
import bts.sio.api.service.OlympiadeService;
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
@Tag(name = "Olympiade", description = "API de gestion des Olympiades")
public class OlympiadeController {

    @Autowired
    private OlympiadeService olympiadeService;

    /**
     * Create - Add a new olympiade
     * @param olympiade An object olympiade
     * @return The olympiade object saved
     */
    @Operation(summary = "Créer une nouvelle Olympiade",
            description = "Ajoute une nouvelle Olympiade à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Olympiade créée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Olympiade.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/olympiade/ajouter")
    public Olympiade createOlympiade(@RequestBody Olympiade olympiade) {
        return olympiadeService.saveOlympiade(olympiade);
    }

    /**
     * Read - Get one olympiade
     * @param id The id of the olympiade
     * @return An Olympiade object full filled
     */
    @Operation(summary = "Obtenir une Olympiade",
            description = "Retourne une Olympiade avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Olympiade trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Olympiade.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Olympiade non trouvée",
                    content = @Content)
    })
    @GetMapping("/olympiade/consulter/{id}")
    public Olympiade getOlympiade(@PathVariable("id") final Long id) {
        Optional<Olympiade> olympiade = olympiadeService.getOlympiade(id);
        return olympiade.orElse(null);
    }

    /**
     * Read - Get all olympiades
     * @return - An Iterable object of Olympiade full filled
     */
    @Operation(summary = "Obtenir toutes les Olympiades",
            description = "Retourne la liste de toutes les Olympiades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste d'Olympiades trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Olympiade.class)) })
    })
    @GetMapping("/olympiade/lister")
    public Iterable<Olympiade> getLesOlympiades() {
        return olympiadeService.getLesOlympiades();
    }

    /**
     * Update - Update an existing olympiade
     * @param id - The id of the olympiade to update
     * @param olympiade - The olympiade object updated
     */
    @Operation(summary = "Mettre à jour une Olympiade",
            description = "Met à jour les informations d'une Olympiade existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Olympiade mise à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Olympiade.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Olympiade non trouvée",
                    content = @Content)
    })
    @PutMapping("/olympiade/modifier/{id}")
    public Olympiade updateOlympiade(@PathVariable("id") final Long id, @RequestBody Olympiade olympiade) {
        Optional<Olympiade> oly = olympiadeService.getOlympiade(id);
        if(oly.isPresent()) {
            Olympiade currentOlympiade = oly.get();

            String numero = olympiade.getNumero();
            if(numero != null) {
                currentOlympiade.setNumero(numero);
            }
            Integer annee = olympiade.getAnnee();
            if(annee != null) {
                currentOlympiade.setAnnee(annee);
            }

            olympiadeService.saveOlympiade(currentOlympiade);
            return currentOlympiade;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an olympiade
     * @param id - The id of the olympiade to delete
     */
    @Operation(summary = "Supprimer une Olympiade",
            description = "Supprime une Olympiade de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Olympiade supprimée avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Olympiade non trouvée",
                    content = @Content)
    })
    @DeleteMapping("/olympiade/supprimer/{id}")
    public void deleteOlympiade(@PathVariable("id") final Long id) {
        olympiadeService.deleteOlympiade(id);
    }

}
