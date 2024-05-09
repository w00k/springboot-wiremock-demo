package com.javaschool.demo.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UtilTest {

  Util util = new Util();

  @Test
  void successURLHttpsTest() {
    String url = "https://www.javachool.com";
    assertEquals(true, util.validUrl(url));
  }

  @Test
  void successURLHttpTest() {
    String url = "http://www.javachool.com";
    assertEquals(true, util.validUrl(url));
  }

  @Test
  void badURLTest() {
    String url = "test@javachool.com";
    assertEquals(false, util.validUrl(url));
  }

  @Test
  void nullURLTest() {
    assertEquals(false, util.validUrl(null));
  }
}