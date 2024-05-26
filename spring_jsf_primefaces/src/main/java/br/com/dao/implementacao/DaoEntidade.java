package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade {

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		
		return null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		
		
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		
		return false;
	}

}
