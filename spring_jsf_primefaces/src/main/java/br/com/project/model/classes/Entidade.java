package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long ent_id;
	private String ent_login = null;
	private String ent_senha = null;
	
	public Long getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(Long ent_id) {
		this.ent_id = ent_id;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}
	
	public String getEnt_login() {
		return ent_login;
	}
	
	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}
	
	public String getEnt_senha() {
		return ent_senha;
	}
	
}
