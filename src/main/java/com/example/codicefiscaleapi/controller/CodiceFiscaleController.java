package com.example.codicefiscaleapi.controller;

import com.example.codicefiscaleapi.dto.RequestDTO;
import com.example.codicefiscaleapi.dto.ResponseDTO;
import com.example.codicefiscaleapi.service.CodiceFiscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/")
public class CodiceFiscaleController {

    private static final Logger logger = LoggerFactory.getLogger(CodiceFiscaleController.class);

    @Autowired
    private CodiceFiscaleService codiceFiscaleService;

    @PostMapping("/getInfoByCodiceFiscale")
    public ResponseEntity<?> getInfoByCodiceFiscale(@RequestBody RequestDTO req) {
        logger.info("Richiesta ricevuta per il codice fiscale: {}", req.getCodiceFiscale());
        try {
            ResponseDTO resp = codiceFiscaleService.getInfoFromCodiceFiscale(req.getCodiceFiscale());
            logger.info("Elaborazione completata con successo per il codice fiscale: {}", req.getCodiceFiscale());
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            logger.error("Errore durante l'elaborazione del codice fiscale: {}", req.getCodiceFiscale(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
