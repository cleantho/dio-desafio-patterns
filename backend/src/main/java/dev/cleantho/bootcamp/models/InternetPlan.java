package dev.cleantho.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

/**
 * 
 * Representa o serviço de Internet
 * 
 * @author Cleantho
 */
@Entity	
@JsonTypeName("Internet")
public class InternetPlan extends Plan{
	private static final long serialVersionUID = 1L;

	@Override
	public String get() {		
		return "NetFlix, Telefone Fixo, McAfee";
	}

	public String getTipo() {
		return "Internet";
	}
}
