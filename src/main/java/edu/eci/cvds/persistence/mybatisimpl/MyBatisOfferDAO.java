package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Offer;
import edu.eci.cvds.persistence.OfferDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.OfferMapper;

public class MyBatisOfferDAO implements OfferDAO {

    @Inject
    private OfferMapper offerMapper;

    @Override
    public void registerOffer(Offer offer) throws PersistenceException {
        try {
            offerMapper.addOffer(offer);
        } catch (org.apache.ibatis.exceptions.PersistenceException exception){
            throw new PersistenceException("Error al registrar oferta",exception);
        }
    }
}
