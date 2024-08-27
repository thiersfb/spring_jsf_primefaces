package br.com.framework.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.engine.SessionFactoryImplementor;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.framework.implementacao.crud.VariavelConexaoUtil;

/**
 * Responsavel por estabelecer a conexão com hibernate
 * @author thiersbarizon
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    public static final String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
    
    @Autowired
    private static SessionFactory sessionFactory=buildSessionFactory();
    
    /**
     * Inicializa a SessionFactory ao iniciar o contexto.
     */
    @PostConstruct
    public void init() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
    }

    /**
     * Fecha a SessionFactory ao destruir o contexto.
     */
    @PreDestroy
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Lê o arquivo de configuração hibernate.cfg.xml e cria a SessionFactory.
     * @return SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
            //return (SessionFactory) sessionFactory.openSession();
             
            
        } catch (Exception e) {
            LOGGER.error("Erro ao criar a SessionFactory", e);
            throw new ExceptionInInitializerError("Erro ao criar a SessionFactory");
        }
    }

    /**
     * Retorna o SessionFactory corrente.
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Retorna a sessão atual do SessionFactory.
     * @return Session
     */
    public static Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    /**
     * Abre uma nova sessão no SessionFactory.
     * @return Session
     */
    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * Obtém a conexão do provedor de conexões configurado.
     * @return Connection SQL
     * @throws SQLException se ocorrer um erro ao obter a conexão
     */
    public static Connection getConnectionProvider() throws SQLException {
        SessionImplementor sessionImplementor = (SessionImplementor) getSessionFactory().openSession();
        return sessionImplementor.getJdbcConnectionAccess().obtainConnection();
    }

    /**
     * Obtém a conexão do DataSource JNDI.
     * @return Connection
     * @throws Exception se ocorrer um erro ao obter a conexão
     */
    public static Connection getConnection() throws Exception {
        InitialContext context = new InitialContext();
        DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
        return ds.getConnection();
    }

    /**
     * Obtém o DataSource JNDI Tomcat.
     * @return DataSource
     * @throws NamingException se ocorrer um erro ao obter o DataSource
     */
    public DataSource getDataSourceJndi() throws NamingException {
        InitialContext context = new InitialContext();
        return (DataSource) context.lookup(VariavelConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
    }
    

}
