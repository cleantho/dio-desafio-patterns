package dev.cleantho.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

/**
 * 
 * Representa o serviço de Telefonia
 * 
 * @author Cleantho
 */
@Entity
@JsonTypeName("Phone")
public class PhonePlan extends Plan {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String get() {
		return "WhatsApp, Instagram, YouTube";
	}

	public String getTipo() {
		return "Celular";
	}
}
