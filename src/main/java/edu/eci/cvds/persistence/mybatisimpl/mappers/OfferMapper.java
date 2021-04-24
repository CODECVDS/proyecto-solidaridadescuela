package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Offer;
import org.apache.ibatis.annotations.Param;

public interface OfferMapper {
    public void addOffer(@Param("offer") Offer offer);
}
