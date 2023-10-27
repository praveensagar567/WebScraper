package com.scraper.api.controller;

import com.scraper.api.model.ResponseDTO;
import com.scraper.api.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    ScraperService scraperService;

    @GetMapping(path = "/partners")
    public List<ResponseDTO> getPartnersData() {
        return scraperService.getPartnersData();
    }

    @GetMapping(path = "/partners-db")
    public List<ResponseDTO> getPartnersDataFromDB() {
        return scraperService.getPartnersDataFromDB();
    }
}
