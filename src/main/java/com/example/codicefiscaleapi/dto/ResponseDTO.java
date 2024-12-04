package com.example.codicefiscaleapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResponseDTO {

	private Integer eta;
	private LocalDate dataNascita;
	
	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	
	
}
