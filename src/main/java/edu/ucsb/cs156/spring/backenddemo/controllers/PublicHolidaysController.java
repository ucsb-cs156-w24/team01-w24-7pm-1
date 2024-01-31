package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;
    @Operation(summary = "Get list of public holidays from year and country code", description = "JSON return format documented here: https://date.nager.at/Api")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHoliday(
        @Parameter(name="year", description="year", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", description="country code", example="us") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHoliday: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}



