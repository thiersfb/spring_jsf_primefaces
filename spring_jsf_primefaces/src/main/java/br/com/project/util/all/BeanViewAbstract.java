package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	@Override
	public void limparLista() throws Exception {

	}

	@Override
	public String salvar() throws Exception {
		
		return null;
	}

	@Override
	public void salvaNaoRetorna() throws Exception {

	}

	@Override
	public void salvarEditar() throws Exception {

	}

	@Override
	public void excluir() throws Exception {

	}

	@Override
	public String ativar() throws Exception {
		
		return null;
	}

	@Override
	public String novo() throws Exception {
		
		return null;
	}

	@Override
	public String editar() throws Exception {
		
		return null;
	}

	@Override
	public void definirVariaveisNulas() throws Exception {

	}

	@Override
	public void consultarEntidade() throws Exception {

	}

	@Override
	public void statusOperacao(EstatusPersistencia sts) throws Exception {
		Messages.responseOperation(sts);
	}
	
	protected void sucesso() throws Exception {
		statusOperacao(EstatusPersistencia.SUCESSO);
	}

	protected void erro() throws Exception {
		statusOperacao(EstatusPersistencia.ERRO);
	}

	@Override
	public String redirecionarNovaEntidade() throws Exception {
		
		return null;
	}

	@Override
	public String redirecionarLocalizaEntidade() throws Exception {
		
		return null;
	}

	@Override
	public void addMsg(String msg) throws Exception {
		Messages.msg(msg);
	}

}
