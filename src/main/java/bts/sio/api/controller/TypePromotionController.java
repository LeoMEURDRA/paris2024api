package bts.sio.api.controller;

import bts.sio.api.model.TypePromotion;
import bts.sio.api.service.TypePromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TypePromotionController {

    @Autowired
    private TypePromotionService typePromotionService;

    /**
     * Create - Add a new typePromotion
     * @param typePromotion An object typePromotion
     * @return The typePromotion object saved
     */
    @PostMapping("/type_promotion/ajouter")
    public TypePromotion createTypePromotion(@RequestBody TypePromotion typePromotion) {
        return typePromotionService.saveTypePromotion(typePromotion);
    }

    /**
     * Read - Get one typePromotion
     * @param id The id of the typePromotion
     * @return An typePromotion object full filled
     */
    @GetMapping("/type_promotion/consulter/{id}")
    public TypePromotion getTypePromotion(@PathVariable("id") final Long id) {
        Optional<TypePromotion> typePromotion = typePromotionService.getTypePromotion(id);
        return typePromotion.orElse(null);
    }

    /**
     * Read - Get all typePromotions
     * @return - An Iterable object of typePromotion full filled
     */
    @GetMapping("/type_promotion/lister")
    public Iterable<TypePromotion> getLesTypePromotions() {
        return typePromotionService.getLesTypePromotions();
    }

    /**
     * Update - Update an existing typePromotion
     * @param id - The id of the typePromotion to update
     * @param typePromotion - The typePromotion object updated
     */
    @PutMapping("/type_promotion/modifier/{id}")
    public TypePromotion updateTypePromotion(@PathVariable("id") final Long id, @RequestBody TypePromotion typePromotion) {
        Optional<TypePromotion> e = typePromotionService.getTypePromotion(id);
        if(e.isPresent()) {
            TypePromotion currentTypePromotion = e.get();

            String libelle = currentTypePromotion.getLibelle();
            if(libelle != null) {
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
    @DeleteMapping("/type_promotion/supprimer/{id}")
    public void deleteTypePromotion(@PathVariable("id") final Long id) {
        typePromotionService.deleteTypePromotion(id);
    }

}