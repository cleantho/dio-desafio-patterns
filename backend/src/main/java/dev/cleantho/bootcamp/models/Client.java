package dev.cleantho.bootcamp.models; 

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * 
 * Representa o Cliente do serviço de Telefonia ou Internet
 * 
 * @author Cleantho
 */
@Entity
public class Client implements Observer, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan picked;
	private String notify;
	@ManyToOne
	@JoinColumn(name = "operador_id")
	private Operator operador;
	
	public Client() {
		super();
	}

	public Client(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Plan getPicked() {
		return picked;
	}

	public void setPicked(Plan picked) {
		this.picked = picked;
	}
	
	public String getNotify() {
		return notify;
	}

	public Operator getOperador() {
		return operador;
	}

	public void setOperador(Operator operador) {
		this.operador = operador;
	}

	@Override
	public void update(String message) {
		this.notify = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", picked=");
		builder.append(picked);
		builder.append(", operador=");
		builder.append(operador);
		builder.append("]");
		return builder.toString();
	}
	
}
