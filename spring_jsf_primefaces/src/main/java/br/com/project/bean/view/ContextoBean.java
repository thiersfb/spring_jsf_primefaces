package br.com.project.bean.view;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.project.model.classes.Entidade;

@Scope(value = "session")
@Component(value = "contextoBean")
public class ContextoBean implements Serializable {
	
	/*
	 * Retorna todas as informações do usuário logado pelo spring security
	 * @return Authentication
	 * */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Entidade getEntidadeLogada() {
		
		return null;
	}
	
}
