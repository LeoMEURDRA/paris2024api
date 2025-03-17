package bts.sio.api.controller;

import bts.sio.api.model.Olympiade;
import bts.sio.api.service.OlympiadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OlympiadeController {

    @Autowired
    private OlympiadeService olympiadeService;

    /**
     * Create - Add a new olympiade
     * @param olympiade An object olympiade
     * @return The olympiade object saved
     */
    @PostMapping("/olympiade/ajouter")
    public Olympiade createOlympiade(@RequestBody Olympiade olympiade) {
        return olympiadeService.saveOlympiade(olympiade);
    }

    /**
     * Read - Get one olympiade
     * @param id The id of the olympiade
     * @return An Olympiade object full filled
     */
    @GetMapping("/olympiade/consulter/{id}")
    public Olympiade getOlympiade(@PathVariable("id") final Long id) {
        Optional<Olympiade> olympiade = olympiadeService.getOlympiade(id);
        return olympiade.orElse(null);
    }

    /**
     * Read - Get all olympiades
     * @return - An Iterable object of Olympiade full filled
     */
    @GetMapping("/olympiade/lister")
    public Iterable<Olympiade> getLesOlympiades() {
        return olympiadeService.getLesOlympiades();
    }

    /**
     * Update - Update an existing olympiade
     * @param id - The id of the olympiade to update
     * @param olympiade - The olympiade object updated
     */
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
    @DeleteMapping("/olympiade/supprimer/{id}")
    public void deleteOlympiade(@PathVariable("id") final Long id) {
        olympiadeService.deleteOlympiade(id);
    }

}