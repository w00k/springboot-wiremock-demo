package com.javachool.demo.service;

import com.javachool.demo.exception.CommunicationException;
import com.javachool.demo.exception.ObjectNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import com.javachool.demo.model.CharacterResponse;
import com.javachool.demo.model.LocationResponse;
import com.javachool.demo.model.RickAndMortyResponse;

import org.springframework.beans.factory.annotation.Value;
import static com.javachool.demo.utils.UtilTransformFile.objectFromJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RickAndMortyServiceTest {

    @Mock 
    private RestTemplate restTemplate;
    
    @Value("${app.endpoint.routers.character.url}")
    private String characterURL;

    @InjectMocks
    private RickAndMortyServiceImpl rickAndMortyService;

    @Test
    void getCharacterByIdWhenSuccessTest() throws IOException {
    //prepate data
    int idCharacter = 1;
    CharacterResponse characterResponse = objectFromJson("/stubs/response_200_character_1.json", CharacterResponse.class);
    LocationResponse locationResponse = objectFromJson("/stubs/response_200_location_3.json", LocationResponse.class);
    String locationURL = "https://rickandmortyapi.com/api/location/3";

    //mock
    when(restTemplate.getForObject(characterURL + idCharacter, CharacterResponse.class)).thenReturn(characterResponse);
    when(restTemplate.getForObject(locationURL , LocationResponse.class)).thenReturn(locationResponse);

    //call service
    RickAndMortyResponse response = rickAndMortyService.callAclService(idCharacter);

    //verify
    assertNotNull(response);
    assertEquals(characterResponse.getName(), response.getName());
    assertEquals(locationResponse.getDimension(), response.getOrigin().getDimension());
  }

    @Test
    void getCharacterByIdWhenCharacterNotFoundTest() throws IOException {
        //prepate data
        int idCharacter = 1001;
        //mock
        when(restTemplate.getForObject(characterURL + idCharacter, CharacterResponse.class))
            .thenThrow(RestClientResponseException.class);

        //verify
        assertThrows(ObjectNotFoundException.class, ()-> rickAndMortyService.callAclService(idCharacter));
    }

    @Test
    void getCharacterByIdWhenExceptionErrorTest() throws IOException {
        //prepate data
        int idCharacter = 1001;
        //mock
        when(restTemplate.getForObject(characterURL + idCharacter, CharacterResponse.class))
            .thenThrow(RestClientException.class);

        //verify
        assertThrows(CommunicationException.class, ()-> rickAndMortyService.callAclService(idCharacter));
    }

    @Test
    void getCharacterByIdWhenLocationNotFoundTest() throws IOException {
        //prepate data
        int idCharacter = 1001;
        CharacterResponse characterResponse = objectFromJson("/stubs/response_200_character_1.json", CharacterResponse.class);
        LocationResponse locationResponse = objectFromJson("/stubs/response_404_location_1001.json", LocationResponse.class);
        String locationURL = "https://rickandmortyapi.com/api/location/3";
        //mock
        when(restTemplate.getForObject(characterURL + idCharacter, CharacterResponse.class)).thenReturn(characterResponse);
        when(restTemplate.getForObject(locationURL , LocationResponse.class)).thenReturn(locationResponse);

        //call service
        RickAndMortyResponse response = rickAndMortyService.callAclService(idCharacter);

        //verify
        assertNotNull(response);
        assertEquals(characterResponse.getName(), response.getName());
        assertNull(response.getOrigin().getDimension());
    }
}
