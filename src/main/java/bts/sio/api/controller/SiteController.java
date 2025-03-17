package bts.sio.api.controller;

import bts.sio.api.model.Site;
import bts.sio.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @Autowired
    private SiteService siteService;

    /**
     * Read - Get all apys
     * @return - An Iterable object of Pays full filled
     */
    @GetMapping("/sites")
    public Iterable<Site> getSites() {
        return siteService.getSites();
    }
}
