package bts.sio.api.controller;

import bts.sio.api.model.Promotion;
import bts.sio.api.model.Sport;
import bts.sio.api.model.TypePromotion;
import bts.sio.api.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * Create - Add a new promotion
     * @param promotion An object promotion
     * @return The promotion object saved
     */
    @PostMapping("/promotion/ajouter")
    public Promotion createPromotion(@RequestBody Promotion promotion) {
        return promotionService.savePromotion(promotion);
    }

    /**
     * Read - Get one promotion
     * @param id The id of the promotion
     * @return An promotion object full filled
     */
    @GetMapping("/promotion/consulter/{id}")
    public Promotion getPromotion(@PathVariable("id") final Long id) {
        Optional<Promotion> promotion = promotionService.getPromotion(id);
        return promotion.orElse(null);
    }

    /**
     * Read - Get all promotions
     * @return - An Iterable object of promotion full filled
     */
    @GetMapping("/promotion/lister")
    public Iterable<Promotion> getLesPromotions() {
        return promotionService.getLesPromotions();
    }

    /**
     * Update - Update an existing promotion
     * @param id - The id of the promotion to update
     * @param promotion - The promotion object updated
     */
    @PutMapping("/promotion/modifier/{id}")
    public Promotion updatePromotion(@PathVariable("id") final Long id, @RequestBody Promotion promotion) {
        Optional<Promotion> e = promotionService.getPromotion(id);
        if(e.isPresent()) {
            Promotion currentPromotion = e.get();

            Integer duree = currentPromotion.getDuree();
            if(duree != null) {
                currentPromotion.setDuree(duree);
            }
            String descriptif = currentPromotion.getDescriptif();
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
    @DeleteMapping("/promotion/supprimer/{id}")
    public void deletePromotion(@PathVariable("id") final Long id) {
        promotionService.deletePromotion(id);
    }

}