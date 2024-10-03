package dev.cleantho.bootcamp.models.dto;

import dev.cleantho.bootcamp.models.Plan;

public class PlanDTO {
	private Integer id;
	private String name;
	private String description;
	private int size;
	private String price;
	private String tipo;
	private String bonus;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	
	public static PlanDTO parser(Plan obj) {
		PlanDTO p = new PlanDTO();			
		p.setId(obj.getId());			
		p.setName(obj.getName());
		p.setPrice(String.valueOf(obj.getPrice()).replace('.', ','));
		p.setSize(obj.getSize());
		p.setDescription(obj.getDescription());
		p.setBonus(obj.get());
		p.setTipo(obj.getTipo());
		return p;
	}

}
