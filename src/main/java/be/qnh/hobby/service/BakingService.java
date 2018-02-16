package be.qnh.hobby.service;

import be.qnh.hobby.domain.CakeTin;
import be.qnh.hobby.domain.Shop;
import be.qnh.hobby.repository.BakingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional  //kan ook hier weggelaten worden en aan individuele methodes worden toegevoegd
public class BakingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BakingService.class);

    @Autowired
    private BakingRepository repo;

    @Autowired
    private Shop shop;

    //@PostConstruct
    @Scheduled(cron = "*/15 * * * * *")
    public Iterable<CakeTin> init() {

        List<CakeTin> cakeTins = new ArrayList<>();

        CakeTin ct1 = new CakeTin();
        ct1.setColour("grey");
        ct1.setMaterial("steel");
        ct1.setShape("round");

        CakeTin ct2 = new CakeTin();
        ct2.setColour("red");
        ct2.setMaterial("silicone");
        ct2.setShape("rectangular");

        CakeTin ct3 = new CakeTin();
        ct3.setColour("white");
        ct3.setMaterial("ceramic");
        ct3.setShape("square");

        //cakeTins.addAll(Arrays.asList(ct1, ct2, ct3));

        this.repo.save(ct1);
        this.repo.save(ct2);
        this.repo.save(ct3);

        //this.repo.save(Arrays.asList(ct1, ct2, ct3));

        //System.out.println("nieuwe items aangemaakt");
        LOGGER.info("add Caketin [{}] and [{}] and [{}]", ct1, ct2, ct3);
        return cakeTins;
    }

    public Iterable<CakeTin> getAll() {

        Iterable<CakeTin> cakeTins = this.repo.findAll();
        return cakeTins;
    }

    public CakeTin findById(long id) {
        CakeTin caketin = this.repo.findOne(id);

       System.err.println("Verkrijgbaar in " + shop.getName() +", " + shop.getCity());

        return caketin;
    }

    public void deleteById(long id){
        this.repo.delete(id);
    }

    public CakeTin addItem(CakeTin cakeTin){
        return this.repo.save(cakeTin);
    }

    public CakeTin updateItem(Long id, CakeTin updatedCakeTin){
        CakeTin itemToBeUpdated = this.repo.findOne(id);

        if (itemToBeUpdated != null){
            itemToBeUpdated.setShape(updatedCakeTin.getShape());
            itemToBeUpdated.setMaterial(updatedCakeTin.getMaterial());
            itemToBeUpdated.setColour(updatedCakeTin.getColour());
            return itemToBeUpdated;
        } else {
            return null;
        }
    }

    public Iterable<CakeTin> findCakeTinByMaterialOrderByShape(String material){
        return this.repo.findCakeTinByMaterialOrderByShape(material);
    }
}
