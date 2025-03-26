package bts.sio.api.service;

import bts.sio.api.model.Promotion;
import bts.sio.api.repository.PromotionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Optional<Promotion> getPromotion(final Long id) {
        return promotionRepository.findById(id);
    }

    public Iterable<Promotion> getLesPromotions() {
        return promotionRepository.findAll();
    }

    public void deletePromotion(final Long id) {
        promotionRepository.deleteById(id);
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

}