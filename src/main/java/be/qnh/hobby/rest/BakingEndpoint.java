package be.qnh.hobby.rest;

import be.qnh.hobby.domain.CakeTin;
import be.qnh.hobby.service.BakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/hobbies")
public class BakingEndpoint {

    @Autowired
    private BakingService service;      //private zodat niemand eraan kan, bovendien kan Spring Boot toch aan de instanties die private zijn

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<CakeTin>> getAll() {
    Iterable<CakeTin> cakeTins = this.service.getAll();

        if (cakeTins == null){
            return new ResponseEntity<Iterable<CakeTin>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Iterable<CakeTin>>(cakeTins, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CakeTin> findById(@PathVariable long id){
        CakeTin result = this.service.findById(id);
        //return result != null ? new ResponseEntity<CakeTin>(result, HttpStatus.OK) : new ResponseEntity<CakeTin>(HttpStatus.NOT_FOUND);

        if (result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CakeTin> deleteById(@PathVariable long id) {
        this.service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<CakeTin> addItem(@RequestBody CakeTin addCakeTin){
        this.service.addItem(addCakeTin);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CakeTin> updateItem(@PathVariable long id, @RequestBody CakeTin updatedCakeTin) {
        this.service.updateItem(id, updatedCakeTin);

        if (updatedCakeTin != null){
            return new ResponseEntity<>(updatedCakeTin, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("material/{material}")
    public ResponseEntity<Iterable<CakeTin>> findCakeTinByMaterialOrderByShape(@PathVariable String material) {
        Iterable<CakeTin> foundByMaterial = this.service.findCakeTinByMaterialOrderByShape(material);

        if (foundByMaterial == null){
            return new ResponseEntity<Iterable<CakeTin>>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Iterable<CakeTin>>(foundByMaterial, HttpStatus.OK);
        }

    }
}