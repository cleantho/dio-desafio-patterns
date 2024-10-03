package dev.cleantho.bootcamp.models.dto;

import dev.cleantho.bootcamp.models.Client;

public class ClientDTO {
	private Long id;

	private String name;

	private int picked;

	private String picked_name;
	
	private String notify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPicked() {
		return picked;
	}

	public void setPicked(int picked) {
		this.picked = picked;
	}

	public String getPicked_name() {
		return picked_name;
	}

	public void setPicked_name(String picked_name) {
		this.picked_name = picked_name;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public static ClientDTO parser(Client client) {
		ClientDTO clientRes = new ClientDTO();
		clientRes.setId(client.getId());
		clientRes.setName(client.getName());
		clientRes.setPicked(client.getPicked().getId());
		clientRes.setPicked_name(client.getPicked().getName());
		clientRes.setNotify(client.getNotify());
		return clientRes;
	}
}
