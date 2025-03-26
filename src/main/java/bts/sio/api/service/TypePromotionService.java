package bts.sio.api.service;

import bts.sio.api.model.TypePromotion;
import bts.sio.api.repository.TypePromotionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class TypePromotionService {

    @Autowired
    private TypePromotionRepository typePromotionRepository;

    public Optional<TypePromotion> getTypePromotion(final Long id) {
        return typePromotionRepository.findById(id);
    }

    public Iterable<TypePromotion> getLesTypePromotions() {
        return typePromotionRepository.findAll();
    }

    public void deleteTypePromotion(final Long id) {
        typePromotionRepository.deleteById(id);
    }

    public TypePromotion saveTypePromotion(TypePromotion typePromotion) {
        return typePromotionRepository.save(typePromotion);
    }

}