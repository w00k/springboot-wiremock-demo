package com.javaschool.demo.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.javaschool.demo.WiremockApplication;
import com.javaschool.demo.utils.UtilTransformFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WiremockApplication.class)
@AutoConfigureMockMvc(print = MockMvcPrint.SYSTEM_OUT)
public class WireMockIntegrationTest {

  static int port = 8089;

  @Autowired
  private MockMvc mockMvc;


  @Rule
  public WireMockRule wireMockRule = new WireMockRule(port);

  @Test
  public void callCervice() throws Exception {
    String responseBodyCharacter = UtilTransformFile.jsonToString("/stubs/response_200_character_1.json");
    String responseBodyLocation = UtilTransformFile.jsonToString("/stubs/response_200_location_3.json");
    String responseExpected = UtilTransformFile.jsonToString("/stubs/response_200_challenge_1.json");

    stubFor(com.github.tomakehurst.wiremock.client.WireMock.get(urlEqualTo("https://rickandmortyapi.com/api/character//1"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(responseBodyCharacter)));

    stubFor(com.github.tomakehurst.wiremock.client.WireMock.get(urlEqualTo("https://rickandmortyapi.com/api/location/3"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(responseBodyLocation)));

    ResultActions resultActions = mockMvc
        .perform(get("http://localhost:" + port + "/rick-and-morty/v1/challenge/1")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    assertNotNull(contentAsString);
    assertEquals(responseExpected, contentAsString);
  }

}