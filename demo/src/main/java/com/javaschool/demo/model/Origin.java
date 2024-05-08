package com.javaschool.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Origin {

    private String name;
    private String url;
    private String dimension;
    private List<String> residents;
}
