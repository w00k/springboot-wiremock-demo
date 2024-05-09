package com.javaschool.demo.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("unused")
public class UtilTransformFile {

  public static <T>T objectFromJson(final String path, final Class<T> targetClass) throws IOException {
    InputStream inputStream = UtilTransformFile.class.getResourceAsStream(path);
    if (inputStream == null) {
      throw new IOException("File not found: " + path);
    }
    Gson gson = new Gson();
    T object = gson.fromJson(new InputStreamReader(inputStream),
        targetClass);
    inputStream.close();
    return object;
  }

  public static String jsonToString(final String path) throws IOException {
    InputStream inputStream = UtilTransformFile.class.getResourceAsStream(path);
    if (inputStream == null) {
      throw new IOException("File not found: " + path);
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
    }
    inputStream.close();
    return stringBuilder.toString();
  }

}
