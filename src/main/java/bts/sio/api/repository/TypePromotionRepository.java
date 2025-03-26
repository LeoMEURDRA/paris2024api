package bts.sio.api.repository;

import bts.sio.api.model.TypePromotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePromotionRepository extends CrudRepository<TypePromotion, Long> {

}