package com.javaschool.demo.controller;

import com.javaschool.demo.config.LoggerConfiguration;
import com.javaschool.demo.model.RickAndMortyResponse;
import com.javaschool.demo.service.RickAndMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RickAndMortyController {

    private final LoggerConfiguration logger = LoggerConfiguration.getLogger("RickAndMortyController");

    @Autowired
    private RickAndMortyService rickAndMortyService;

    @GetMapping("/rick-and-morty/v1/challenge/{id}")
    public ResponseEntity<RickAndMortyResponse> callRickAndMortyController(@PathVariable("id") int idCharacter) {
        logger.info("callRickAndMortyController start");
        return new ResponseEntity<>(rickAndMortyService.callAclService(idCharacter), HttpStatus.OK);
    }
}
