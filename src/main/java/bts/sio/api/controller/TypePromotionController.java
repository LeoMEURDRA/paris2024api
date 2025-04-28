package bts.sio.api.controller;

import bts.sio.api.model.TypePromotion;
import bts.sio.api.service.TypePromotionService;
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
@Tag(name = "TypePromotion", description = "API de gestion des types de promotions")
public class TypePromotionController {

    @Autowired
    private TypePromotionService typePromotionService;

    /**
     * Create - Add a new typePromotion
     * @param typePromotion An object typePromotion
     * @return The typePromotion object saved
     */
    @Operation(summary = "Créer un nouveau type de promotion",
            description = "Ajoute un nouveau type de promotion à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Type de promotion créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypePromotion.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/type_promotion/ajouter")
    public TypePromotion createTypePromotion(@RequestBody TypePromotion typePromotion) {
        return typePromotionService.saveTypePromotion(typePromotion);
    }

    /**
     * Read - Get one typePromotion
     * @param id The id of the typePromotion
     * @return An typePromotion object full filled
     */
    @Operation(summary = "Obtenir un type de promotion",
            description = "Retourne un type de promotion avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Type de promotion trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypePromotion.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Type de promotion non trouvé",
                    content = @Content)
    })
    @GetMapping("/type_promotion/consulter/{id}")
    public TypePromotion getTypePromotion(@PathVariable("id") final Long id) {
        Optional<TypePromotion> typePromotion = typePromotionService.getTypePromotion(id);
        return typePromotion.orElse(null);
    }

    /**
     * Read - Get all typePromotions
     * @return - An Iterable object of typePromotion full filled
     */
    @Operation(summary = "Obtenir tous les types de promotions",
            description = "Retourne la liste de tous les types de promotions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des types de promotions trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypePromotion.class)) })
    })
    @GetMapping("/type_promotion/lister")
    public Iterable<TypePromotion> getLesTypePromotions() {
        return typePromotionService.getLesTypePromotions();
    }

    /**
     * Update - Update an existing typePromotion
     * @param id - The id of the typePromotion to update
     * @param typePromotion - The typePromotion object updated
     */
    @Operation(summary = "Mettre à jour un type de promotion",
            description = "Met à jour les informations d'un type de promotion existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Type de promotion mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypePromotion.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Type de promotion non trouvé",
                    content = @Content)
    })
    @PutMapping("/type_promotion/modifier/{id}")
    public TypePromotion updateTypePromotion(@PathVariable("id") final Long id, @RequestBody TypePromotion typePromotion) {
        Optional<TypePromotion> e = typePromotionService.getTypePromotion(id);
        if (e.isPresent()) {
            TypePromotion currentTypePromotion = e.get();

            String libelle = typePromotion.getLibelle();
            if (libelle != null) {
                currentTypePromotion.setLibelle(libelle);
            }

            typePromotionService.saveTypePromotion(currentTypePromotion);
            return currentTypePromotion;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a typePromotion
     * @param id - The id of the typePromotion to delete
     */
    @Operation(summary = "Supprimer un type de promotion",
            description = "Supprime un type de promotion de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Type de promotion supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Type de promotion non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/type_promotion/supprimer/{id}")
    public void deleteTypePromotion(@PathVariable("id") final Long id) {
        typePromotionService.deleteTypePromotion(id);
    }

}
