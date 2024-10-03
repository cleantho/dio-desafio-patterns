package dev.cleantho.bootcamp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

/**
 * Singleton e Classe Subject do padrão Observer(somente o método "Notify")
 * Empresa que prover o serviço de Telefonia ou Internet
 * 
 * @author Cleantho
 */
@Entity
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;
	@Transient
	private static Operator instance;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "operador")
	@JsonIgnore
    private List<Client> clients;

	private Operator() {
        clients = new ArrayList<>();
    }

    public static Operator getInstance() {
        if (instance == null) {
            instance = new Operator();
        }
        return instance;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public List<Client> getClients() {
		return clients;
	}
    
    public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void addClient(Client client) {
        clients.add(client);
    }

    public void notifyClients(String message) {
        for (Observer client : clients) {
            client.update(message);
        }
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Operator [");
		builder.append(id);
		builder.append(" : ");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
