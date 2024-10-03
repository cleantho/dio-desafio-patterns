package dev.cleantho.bootcamp.models;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * 
 * @author Cleantho
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private int size;
	private double price;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Plan [");
		builder.append(id);
		builder.append(" : ");
		builder.append(name);
		builder.append(" : ");
		builder.append(size);
		builder.append("Mb]");
		return builder.toString();
	}

	/*
	 * Implements different returns
	 */
	public abstract String get();
	
	public abstract String getTipo();
}
