
package com.example.codicefiscaleapi.controller;

import com.example.codicefiscaleapi.service.CodiceFiscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/codicefiscale")
public class CodiceFiscaleController {

    @Autowired
    private CodiceFiscaleService codiceFiscaleService;

    @GetMapping("/{codiceFiscale}")
    public Map<String, Object> getInfoByCodiceFiscale(@PathVariable String codiceFiscale) {
        return codiceFiscaleService.getInfoFromCodiceFiscale(codiceFiscale);
    }
}
