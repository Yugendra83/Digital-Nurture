package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private List<Country> countryList;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryList;
    }
}
