package nl.abnamro.polar.assessment.dates.api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import nl.abnamro.polar.assessment.dates.api.exception.DateFormatException;
import nl.abnamro.polar.assessment.dates.api.model.DateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@RestController
@RequestMapping(path = "/api")
public class DatesController {

    @GetMapping(path = "/difference-in-days/{inputDate}")
    public ResponseEntity<DateResponse> getDifferenceInDays(@Parameter(description = "input date to calculate days from now, formats supported are : {'dd-MM-yyyy', 'yyyy-MM-dd', 'MM-dd-yyyy'}") @PathVariable String inputDate) {
        DateResponse dateResponse = new DateResponse();
        String[] dateFormats = {"dd-MM-yyyy", "yyyy-MM-dd", "MM-dd-yyyy"};
        LocalDate parsedLocalDate = LocalDate.now();
        int count = 0;
        for (String format : dateFormats) {
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.getDefault());
                parsedLocalDate = LocalDate.parse(inputDate, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                count++;
            }
        }
        if (count == 3) {
            throw new DateFormatException(String.format("Input Date %s is not in any supported format", inputDate));
        }
        long betweenDays = ChronoUnit.DAYS.between(parsedLocalDate, ZonedDateTime.now());
        dateResponse.setDays(Math.abs((int) betweenDays));
        return ResponseEntity.ok(dateResponse);
    }
}
