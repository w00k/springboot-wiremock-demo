package com.javachool.demo.service;

import com.javachool.demo.config.LoggerConfiguration;
import com.javachool.demo.exception.CommunicationException;
import com.javachool.demo.exception.ObjectNotFoundException;
import com.javachool.demo.model.*;
import com.javachool.demo.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("RickAndMortyServiceImpl");

    RestTemplate restTemplate = new RestTemplate();

    @Value("${app.endpoint.routers.character.url}")
    private String character;

    public RickAndMortyResponse callAclService(int idCharacter) {

        logger.info("callAclService start");

        CharacterResponse characterResponse = new CharacterResponse();
        LocationResponse locationResponse = new LocationResponse();
        String locationRequest = null;
        Util util = new Util();

        try {
            logger.info("call CharacterService");
            characterResponse = restTemplate.getForObject(character + idCharacter, CharacterResponse.class);


            //get URL for Location
            locationRequest = characterResponse.getLocation().getUrl().toString();
            if (util.validUrl(locationRequest) == true) {
                logger.info("URL Location is valid call LocationService");
                locationResponse = restTemplate.getForObject(locationRequest, LocationResponse.class);
            }

        } catch (RestClientResponseException e) {
            logger.error(e.getMessage());
            throw new ObjectNotFoundException("Character not found");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommunicationException("Communication error");
        }

        return rickAndMortyResponseObject(characterResponse, locationResponse);
    }

    public RickAndMortyResponse rickAndMortyResponseObject(CharacterResponse characterResponse, LocationResponse locationResponse) {

        logger.info("rickAndMortyResponseObject start");

        RickAndMortyResponse rickAndMortyResponse = new RickAndMortyResponse();
        Origin origin = new Origin();

        //set Origin object
        if (locationResponse != null) {
            origin.setName(locationResponse.getName());
            origin.setUrl(locationResponse.getUrl());
            origin.setDimension(locationResponse.getDimension());
            origin.setResidents(locationResponse.getResidents());
        }

        //set RickAndMortyResponse object
        rickAndMortyResponse.setId(characterResponse.getId());
        rickAndMortyResponse.setName(characterResponse.getName());
        rickAndMortyResponse.setStatus(characterResponse.getStatus());
        rickAndMortyResponse.setSpecies(characterResponse.getSpecies());
        rickAndMortyResponse.setType(characterResponse.getType());
        rickAndMortyResponse.setEpisodeCount(String.valueOf(characterResponse.getEpisode().size()));
        rickAndMortyResponse.setOrigin(origin);

        return rickAndMortyResponse;
    }
}
