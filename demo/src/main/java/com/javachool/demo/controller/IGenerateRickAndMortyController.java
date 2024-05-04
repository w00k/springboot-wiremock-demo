package com.javachool.demo.controller;

import com.javachool.demo.model.RickAndMortyResponse;
import org.springframework.http.ResponseEntity;

public interface IGenerateRickAndMortyController {

    public ResponseEntity<RickAndMortyResponse> generateOtp(RickAndMortyResponse request);
}
