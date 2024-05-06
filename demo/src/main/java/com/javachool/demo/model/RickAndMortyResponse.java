package com.javachool.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyResponse {

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String episodeCount;
    private Origin origin;

}
