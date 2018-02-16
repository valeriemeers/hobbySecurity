package be.qnh.hobby.service;

import be.qnh.hobby.domain.CakeTin;
import be.qnh.hobby.domain.Shop;
import be.qnh.hobby.repository.BakingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class BakingServiceUnitTest {

    @InjectMocks    //creates an instance of the class and injects the mocks that are created with the @Mock
    BakingService bakingService;

    @Mock           //creates a mock
    BakingRepository repo;

    @Mock
    Shop shop;

    @Mock
    CakeTin cakeTin;

    @Test
    public void testFindById() {

        CakeTin resultFromRepo = new CakeTin();
        resultFromRepo.setColour("green");
        resultFromRepo.setShape("oval");
        resultFromRepo.setMaterial("steel");

        // instruct the mock repo what to do
        Mockito.when(this.repo.findOne(3L)).thenReturn(resultFromRepo);

        // mocking done
        CakeTin resultFromService = this.bakingService.findById(3);

        Assert.assertEquals("green", resultFromService.getColour());
        //Assert.assertTrue(resultFromService.isMixed());

        Mockito.verify(this.repo).findOne(3L);
        Mockito.verify(this.repo, Mockito.times(3)).findOne(3L); //the same but explicit one call

    }
}
