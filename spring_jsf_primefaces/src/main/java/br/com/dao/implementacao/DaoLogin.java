package br.com.dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.repository.interfaces.RepositoryLogin;

@Repository
public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		
		String sql = "SELECT COUNT(1) AS autentica FROM Entidade WHERE ent_login = ? AND ent_senha = ? ";
		SqlRowSet sqlRowSet = super.getSimpleJdbcTemplate().queryForRowSet(sql, new Object[]{login, senha});
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 : false;
		//return false;
	}

}
