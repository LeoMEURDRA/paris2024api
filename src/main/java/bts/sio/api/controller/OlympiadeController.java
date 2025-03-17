package bts.sio.api.controller;

import bts.sio.api.model.Olympiade;
import bts.sio.api.service.OlympiadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlympiadeController {

    @Autowired
    private OlympiadeService olympiadeService;

    /**
     * Read - Get all apys
     * @return - An Iterable object of Pays full filled
     */
    @GetMapping("/olympiades")
    public Iterable<Olympiade> getOlympiades() {
        return olympiadeService.getLesOlympiades();
    }
}
