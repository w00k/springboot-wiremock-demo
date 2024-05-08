package com.javaschool.demo.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {

    private int id;
    private String name;
    private String type;
    private String dimension;
    List<String> residents;
    private String url;
    private Date created;

}
