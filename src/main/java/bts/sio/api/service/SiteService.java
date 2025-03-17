package bts.sio.api.service;

import bts.sio.api.model.Site;
import bts.sio.api.repository.SiteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Data
@Service
public class SiteService {
    @Autowired
    private SiteRepository siteRepository;

    public Optional<Site> getSite(final Long id) {
        return siteRepository.findById(id);
    }

    public Iterable<Site> getSites() {
        return siteRepository.findAll();
    }

    public void deleteSite(final Long id) {
        siteRepository.deleteById(id);
    }

    public Site saveSport(Site site) {
        Site savedSite = siteRepository.save(site);
        return savedSite;
    }
}
