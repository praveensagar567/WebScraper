package com.scraper.api.service;

import com.scraper.api.model.ResponseDTO;

import java.util.List;

public interface ScraperService {

    List<ResponseDTO> getPartnersData();

    List<ResponseDTO> getPartnersDataFromDB();
}
