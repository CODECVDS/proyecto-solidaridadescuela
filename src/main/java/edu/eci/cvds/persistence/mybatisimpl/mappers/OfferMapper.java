package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Offer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfferMapper {
    public void addOffer(@Param("offer") Offer offer);
    public void updateOffer(@Param("offer") Offer offer);
    public Offer loadOffer(@Param("id") int id);
    public List<Offer> loadAllOffers();
    public List<Offer> loadAllOffersWS();
    public int loadParamNOffer();
}
