package br.com.framework.implementacao.crud;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void persist(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public T merge(T obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findList(Class<T> objs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findPorId(Class<T> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findListByDynamicQuery(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeUpdateDynamicQuery(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateSQLDynamic(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void evict(Object objs) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getListSQLDynamic(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	
	/*
	public SimpleJdbcClassImpl getSimpleJdbcClass() {
		return simpleJdbcClass;
	}
	*/
	
	@Override
	public Long totalRegistros(String table) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query obterQuery(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findListByDynamicQuery(String query, int iniciaNoRegistro, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
