

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
	
	/**
	 * salva os dados
	 * @param obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;
	
	/**
	 * salva objeto
	 * @param obj
	 * @throws Exception
	 */
	void persist(T obj) throws Exception;
	
	/**
	 * salva ou atualiza
	 * @param obj
	 * @throws Exception
	 */
	void saveOrUpdate(T obj) throws Exception;
	
	/**
	 * atualiza os dados
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;

	/**
	 * exclui os dados
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;

	/**
	 * salva e retorna o objeto em estado persistente
	 * @param obj
	 * @throws Exception
	 */
	T merge(T obj) throws Exception;
	
	/**
	 * carrega a lista de dados de determinada classe
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	List<T> findList(Class<T> objs) throws Exception;
	
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	T findPorId(Class<T> entidade, Long id) throws Exception;
	
	List<T> findListByDynamicQuery(String s) throws Exception;
	
	/**
	 * executa update com HQL do Hibernate/JAP
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateDynamicQuery(String s) throws Exception;

	/**
	 * executa update com SQL puro
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateSQLDynamic(String s) throws Exception;
	
	/**
	 * limpa a sessão do Hibernate
	 * @throws Exception
	 */
	void clearSession() throws Exception;
	
	/**
	 * retira um objeto da sessão do Hibernate
	 * @param objs
	 * @throws Exception
	 */
	void evict(Object objs) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSQLDynamic(String sql) throws Exception;
	
	//JDBC do SpringFramework
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	Long totalRegistros(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	/**
	 * Carregamento dinamico com JSF e PrimeFaces
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maxResult
	 * @return
	 * @throws Exception
	 */
	List<T> findListByDynamicQuery(String query, int iniciaNoRegistro, int maxResult) throws Exception;
	
	
}
