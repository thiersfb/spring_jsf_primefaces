package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	//@Autowired
	//private SimpleJdbcClassImpl simpleJdbcClass;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplateImpl;
	
	public SimpleJdbcTemplate getSimpleJdbcTemplateImpl() {
		return simpleJdbcTemplateImpl;
	}
	
	@Override
	public void save(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@Override
	public T merge(T obj) throws Exception {
		validateSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validateSessionFactory();
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT DISTINCT(entity) FROM ")
			.append(entidade.getSimpleName())
			.append(" entity ");
		
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validateSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public T findPorId(Class<T> entidade, Long id) throws Exception {
		validateSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public List<T> findListByDynamicQuery(String s) throws Exception {
		validateSessionFactory();
				
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		
		return lista;
	}

	/**
	 * Utiliza HQL
	 */
	@Override
	public void executeUpdateDynamicQuery(String s) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		executeFlushSession();
	}

	/**
	 * Utiliza SQL puro
	 */
	@Override
	public void executeUpdateSQLDynamic(String s) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object objs) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().evict(objs);
	}

	@Override
	public Session getSession() throws Exception {
		validateSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDynamic(String sql) throws Exception {
		validateSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}
	
	@Override
	public Long totalRegistros(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from ").append(table);
		return jdbcTemplate.queryForLong(sql.toString());
	}
	@Override
	public Query obterQuery(String query) throws Exception {
		validateSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		
		return queryReturn;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no parâmetro -> iniciaNoRegistro e obtendo o maximo de
	 * resultados passados em -> maxResult
	 * 
	 * @param query
	 * @param iniciaNoRegistro
	 * @param MaxResult
	 * return List<T>
	 * @throws Exception
	 */
	@Override
	public List<T> findListByDynamicQuery(String query, int iniciaNoRegistro, int maxResult) throws Exception {
		validateSessionFactory();
		
		List<T> lista = new ArrayList<T>();
		
		lista = sessionFactory.getCurrentSession().createQuery(query)
				.setFirstResult(iniciaNoRegistro)
				.setMaxResults(maxResult)
				.list();
		
		return lista;
	}
	
	
	private void validateTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	private void validateSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		validateTransaction();
	}
	
	private void commitProcessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	
	private void rollbackProcessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	/**
	 * Roda Instantaneamente a instrução SQL no Banco de Dados
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
	
	/**
	 * Retorna lista genérica de objetos genéricos
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getArrayListDynamicSQL(String sql) throws Exception {
		validateSessionFactory();
		
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		
		return lista;
	}

	public T findUniqueByQueryDinamica(String query) throws Exception {
		validateSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
		return obj;
	}

	public T findUniqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		validateSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ")
			.append(entidade.getSimpleName())
			.append(" entity where entity.")
			.append(atributo).append(" = '").append(valor).append("' ")
			.append(condicao);
		
		T obj = (T) this.findUniqueByQueryDinamica(query.toString());
		
		return obj;
	}
	
	
}
