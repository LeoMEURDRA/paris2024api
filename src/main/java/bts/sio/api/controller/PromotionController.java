package bts.sio.api.controller;

import bts.sio.api.model.Promotion;
import bts.sio.api.model.Sport;
import bts.sio.api.model.TypePromotion;
import bts.sio.api.service.PromotionService;
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
@Tag(name = "Promotion", description = "API de gestion des promotions des athlètes")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * Create - Add a new promotion
     * @param promotion An object promotion
     * @return The promotion object saved
     */
    @Operation(summary = "Créer une nouvelle promotion",
            description = "Ajoute une nouvelle promotion à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Promotion créée avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Promotion.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/promotion/ajouter")
    public Promotion createPromotion(@RequestBody Promotion promotion) {
        return promotionService.savePromotion(promotion);
    }

    /**
     * Read - Get one promotion
     * @param id The id of the promotion
     * @return A promotion object filled
     */
    @Operation(summary = "Obtenir une promotion",
            description = "Retourne une promotion avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Promotion trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Promotion.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Promotion non trouvée",
                    content = @Content)
    })
    @GetMapping("/promotion/consulter/{id}")
    public Promotion getPromotion(@PathVariable("id") final Long id) {
        Optional<Promotion> promotion = promotionService.getPromotion(id);
        return promotion.orElse(null);
    }

    /**
     * Read - Get all promotions
     * @return - An Iterable object of promotions full filled
     */
    @Operation(summary = "Obtenir toutes les promotions",
            description = "Retourne la liste de toutes les promotions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des promotions trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Promotion.class)) })
    })
    @GetMapping("/promotion/lister")
    public Iterable<Promotion> getLesPromotions() {
        return promotionService.getLesPromotions();
    }

    /**
     * Update - Update an existing promotion
     * @param id - The id of the promotion to update
     * @param promotion - The promotion object updated
     */
    @Operation(summary = "Mettre à jour une promotion",
            description = "Met à jour les informations d'une promotion existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Promotion mise à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Promotion.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Promotion non trouvée",
                    content = @Content)
    })
    @PutMapping("/promotion/modifier/{id}")
    public Promotion updatePromotion(@PathVariable("id") final Long id, @RequestBody Promotion promotion) {
        Optional<Promotion> e = promotionService.getPromotion(id);
        if(e.isPresent()) {
            Promotion currentPromotion = e.get();

            Integer duree = promotion.getDuree();
            if(duree != null) {
                currentPromotion.setDuree(duree);
            }
            String descriptif = promotion.getDescriptif();
            if(descriptif != null) {
                currentPromotion.setDescriptif(descriptif);
            }
            TypePromotion type = promotion.getType();
            if(type != null) {
                currentPromotion.setType(type);
            }
            Sport sport = promotion.getSport();
            if(sport != null) {
                currentPromotion.setSport(sport);
            }

            promotionService.savePromotion(currentPromotion);
            return currentPromotion;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a promotion
     * @param id - The id of the promotion to delete
     */
    @Operation(summary = "Supprimer une promotion",
            description = "Supprime une promotion de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Promotion supprimée avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Promotion non trouvée",
                    content = @Content)
    })
    @DeleteMapping("/promotion/supprimer/{id}")
    public void deletePromotion(@PathVariable("id") final Long id) {
        promotionService.deletePromotion(id);
    }

}
