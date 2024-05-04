package com.javachool.demo.controller;

import com.javachool.demo.config.LoggerConfiguration;
import com.javachool.demo.model.RickAndMortyResponse;
import com.javachool.demo.service.RickAndMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RickAndMortyController {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("RickAndMortyController");

    @Autowired
    private RickAndMortyService rickAndMortyService;

    @GetMapping("/rick-and-morty/v1/challenge/{id}")
    public ResponseEntity<RickAndMortyResponse> callRickAndMortyController(@PathVariable("id") int idCharacter) {
        logger.info("callRickAndMortyController start");
        return new ResponseEntity<>(rickAndMortyService.callAclService(idCharacter), HttpStatus.OK);
    }
}
