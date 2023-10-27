
package com.scraper.api.service;

import com.scraper.api.model.ResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperServiceImpl implements ScraperService {

    private static final String PARTNERS_URL = "https://newbreedtraining.com/partners/";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ResponseDTO> getPartnersData() {
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        try {
            Document document = Jsoup.connect(PARTNERS_URL).get();
            Elements partnerElements = document.getElementsByClass("fl-builder-content fl-builder-content-5086 fl-builder-content-primary fl-builder-global-templates-locked");

            for (Element partnerElement : partnerElements) {
                ResponseDTO responseDTO = new ResponseDTO();

                // Search for the title element
                Element titleElement = partnerElement.selectFirst(".fl-heading");
                if (titleElement != null) {
                    responseDTO.setTitle(titleElement.text());
                }

                // Search for the description element
                Element descriptionElement = partnerElement.selectFirst(".fl-rich-text");
                if (descriptionElement != null) {
                    responseDTO.setDescription(descriptionElement.text());
                }

                // Search for the icon element
                Element iconElement = partnerElement.selectFirst(".fl-photo-content fl-photo-img-png");
                if (iconElement != null) {
                    responseDTO.setIconUrl(iconElement.absUrl("src"));
                }

                mongoTemplate.save(responseDTO);
                responseDTOS.add(responseDTO);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return responseDTOS;
    }

    @Override
    public List<ResponseDTO> getPartnersDataFromDB() {
        return mongoTemplate.findAll(ResponseDTO.class);
    }
}
