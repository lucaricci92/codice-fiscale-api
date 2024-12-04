
package com.example.codicefiscaleapi.service;

import org.springframework.stereotype.Service;

import com.example.codicefiscaleapi.dto.ResponseDTO;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CodiceFiscaleService {

	public ResponseDTO getInfoFromCodiceFiscale(String codiceFiscale) {

		String cfPattern = "^[A-Z]{6}[0-9]{2}[ABCDEHLMPRST][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
	    if (codiceFiscale == null || !codiceFiscale.matches(cfPattern)||codiceFiscale.isEmpty()) {
	        throw new IllegalArgumentException("Codice fiscale non valido o non inserito: " + codiceFiscale);
	    }

	    try {
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
	        Integer age = Period.between(birthDate, LocalDate.now()).getYears();

	        ResponseDTO response = new ResponseDTO();
	        response.setDataNascita(birthDate);
	        response.setEta(age);

	        return response;

	    } catch (Exception e) {
	        throw new RuntimeException("Errore durante l'elaborazione del codice fiscale: " + codiceFiscale, e);
	    }
	}
}
