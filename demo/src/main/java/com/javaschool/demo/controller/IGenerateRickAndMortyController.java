package com.javaschool.demo.controller;

import com.javaschool.demo.model.RickAndMortyResponse;
import org.springframework.http.ResponseEntity;

public interface IGenerateRickAndMortyController {

    public ResponseEntity<RickAndMortyResponse> generateOtp(RickAndMortyResponse request);
}
