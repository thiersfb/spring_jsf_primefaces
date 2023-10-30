package br.com.project.util.all;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public interface ActionViewPadrao extends Serializable {
	
	abstract void limparLista() throws Exception;
	
	abstract String salvar() throws Exception;
	
	abstract void salvaNaoRetorna() throws Exception;
	
	abstract void salvarEditar() throws Exception;
	
	abstract void excluir() throws Exception;
	
	abstract String ativar() throws Exception;
	
	/**
	 * 
	 * @PostConstruct realiza inicialização de metodos, valores ou variaveis
	 * @throws Exception
	 */
	@PostConstruct
	abstract String novo() throws Exception;
	
	abstract String editar() throws Exception;
	
	abstract void definirVariaveisNulas() throws Exception;
	
	abstract void consultarEntidade() throws Exception;
	
	abstract void statusOperacao(EstatusPersistencia sts) throws Exception;
	
	abstract String redirecionarNovaEntidade() throws Exception;
	
	abstract String redirecionarLocalizaEntidade() throws Exception;
	
	abstract void addMsg(String msg) throws Exception;
	
	
	
}
