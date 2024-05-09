package com.javaschool.demo.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.javaschool.demo.WiremockApplication;
import com.javaschool.demo.utils.UtilTransformFile;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.hc.client5.http.classic.methods.HttpGet;
import wiremock.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import wiremock.org.apache.hc.client5.http.impl.classic.HttpClients;
import wiremock.org.apache.hc.core5.http.HttpResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WiremockApplication.class)
public class WireMockIntegrationTest {

  static int port = 8089;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(port);

  @Test
  public void callCervice() throws IOException {
    String responseBodyCharacter = UtilTransformFile.jsonToString("/stubs/response_200_character_1.json");
    String responseBodyLocation = UtilTransformFile.jsonToString("/stubs/response_200_location_3.json");

    stubFor(com.github.tomakehurst.wiremock.client.WireMock.get(urlEqualTo("/api/character/"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(responseBodyCharacter)));

    stubFor(com.github.tomakehurst.wiremock.client.WireMock.get(urlEqualTo("/api/location/"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(responseBodyLocation)));

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(String.format("http://localhost:%s/rick-and-morty/v1/challenge/1", port));
    HttpResponse httpResponse = httpClient.execute(request);
    assertNotNull(httpResponse);

  }

}