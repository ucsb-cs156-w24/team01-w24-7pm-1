package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Public Holidays info from year and country code")
@Slf4j
@RestController
@RequestMapping("/api/publicholiday")
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary = "Get list of public holidays from year and country code", description = "JSON return format documented here: https://date.nager.at/Api")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHoliday(
        @Parameter(name="year", description="number fo years", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", description="code of country", example="us") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHoliday: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}



