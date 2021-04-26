package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Offer;
import edu.eci.cvds.persistence.OfferDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.OfferMapper;
import edu.eci.cvds.services.ServicesException;

import java.util.List;

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

    @Override
    public void updateOffer(Offer offer) throws PersistenceException {
        try {
            offerMapper.updateOffer(offer);
        } catch (org.apache.ibatis.exceptions.PersistenceException exception){
            throw new PersistenceException("Error al actualizar oferta",exception);
        }
    }

    @Override
    public Offer loadOffer(int offerId) throws PersistenceException {
        try {
            return offerMapper.loadOffer(offerId);
        } catch (org.apache.ibatis.exceptions.PersistenceException exception){
            throw new PersistenceException("Error al cargar oferta",exception);
        }
    }

    @Override
    public List<Offer> loadAllOffers() throws PersistenceException {
        try {
            return offerMapper.loadAllOffers();
        } catch (org.apache.ibatis.exceptions.PersistenceException exception){
            exception.printStackTrace();
            throw new PersistenceException("Error al cargar las ofertas",exception);
        }
    }

    @Override
    public int loadParamNOffer() throws PersistenceException {
        try {
            return offerMapper.loadParamNOffer();
        } catch (org.apache.ibatis.exceptions.PersistenceException exception){
            throw new PersistenceException("Error al cargar parametro nOffer",exception);
        }
    }
}
