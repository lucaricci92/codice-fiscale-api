
package com.example.codicefiscaleapi.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Service
public class CodiceFiscaleService {

    public Map<String, Object> getInfoFromCodiceFiscale(String codiceFiscale) {
        String yearPart = codiceFiscale.substring(6, 8);
        String monthPart = codiceFiscale.substring(8, 9);
        String dayPart = codiceFiscale.substring(9, 11);

        int year = Integer.parseInt(yearPart);
        year += (year < 30) ? 2000 : 1900;
        int month = "ABCDEHLMPRST".indexOf(monthPart.toUpperCase()) + 1;
        int day = Integer.parseInt(dayPart);
        if (day > 31) {
            day -= 40;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);
        int age = Period.between(birthDate, LocalDate.now()).getYears();

        Map<String, Object> response = new HashMap<>();
        response.put("dataNascita", birthDate);
        response.put("eta", age);
        return response;
    }
}
