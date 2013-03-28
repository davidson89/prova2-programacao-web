package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class BaseDAOFactory<T> implements GenericBaseDAO<T> {

	private Class<T> clazz;

	public BaseDAOFactory(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void salve(T persistivel) {
		try {
			Session session = BaseDAOFactory.getSession();
			session.beginTransaction();
			session.saveOrUpdate(persistivel);
			session.getTransaction().commit();
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(T persistivel) {
		try {
			Session session = BaseDAOFactory.getSession();
			session.beginTransaction();
			session.delete(persistivel);
			session.getTransaction().commit();
		} finally {
			this.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByPk(Long id) {
		try {
			Criteria criteria = BaseDAOFactory.getSession().createCriteria(this.clazz);
			return (T) criteria.add(Restrictions.idEq(id)).uniqueResult();
		} finally {
			this.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		try {
			Criteria criteria = BaseDAOFactory.getSession().createCriteria(this.clazz);
			return criteria.list();
		} finally {
			this.close();
		}
	}


	private static final SessionFactory sessionFactory;

	/**
	 * Utiliza o arquivo hibernate.cfg.xml, buscando as informações de configuração. É aqui que os models são
	 * capturados, caso a entidade mapeada não exista ela é criada. Caso seja modificada é atualizada. Não deleta as
	 * tabelas.
	 */
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * @return Retorna a sessão.
	 * @throws HibernateException Excessões relacionados ao banco de dados.
	 */
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	/**
	 * Fecha uma sessão.
	 */
	public void close() {
		if (getSession().isOpen() && getSession() != null) {
			getSession().close();
		}
	}
}
