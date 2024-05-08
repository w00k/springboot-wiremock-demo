package com.javaschool.demo.controller;

public class WireMockIntegrationTest {

  static int port = 9999;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(port);

}