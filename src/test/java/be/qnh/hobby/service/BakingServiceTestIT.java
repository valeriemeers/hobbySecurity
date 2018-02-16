package be.qnh.hobby.service;

import be.qnh.hobby.HobbyApplication;
import be.qnh.hobby.domain.CakeTin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")     //waar kan ik mijn db uitlezen
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BakingServiceTestIT {              //Door IT te plaatsen achteraan de klassenaam, gaat maven de test pas uitvoeren op het einde... bij "verify" of "install"

    @Autowired
    private BakingService bakingService;

    @Test
    public void testCrud(){
        CakeTin nieuwe = new CakeTin();
        nieuwe.setMaterial("tin");
        nieuwe.setColour(("blue"));
        nieuwe.setShape("oval");

        //CREATE
        CakeTin cakeTinCreated = this.bakingService.addItem(nieuwe);
        Assert.assertTrue(cakeTinCreated.getId() != 0);

        //READ
        long id = cakeTinCreated.getId();
        CakeTin opgehaald = this.bakingService.findById(id);
        Assert.assertEquals(nieuwe.getMaterial(), opgehaald.getMaterial());

        //UPDATE
        opgehaald.setColour(("yellow"));
        CakeTin opgehaaldEnSaved = this.bakingService.updateItem(id, opgehaald);
        Assert.assertEquals("update test mislukt", "yellow", opgehaaldEnSaved.getColour());

        //DELETE
        this.bakingService.deleteById(id);
        Assert.assertNull(this.bakingService.findById(id));

    }
}
