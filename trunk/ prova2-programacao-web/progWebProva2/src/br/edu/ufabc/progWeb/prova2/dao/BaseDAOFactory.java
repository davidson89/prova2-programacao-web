package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

import org.hibernate.Criteria;
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
			Session session = this.getSession();
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
			Session session = this.getSession();
			session.beginTransaction();
			session.delete(persistivel);
			session.getTransaction().commit();
		} finally {
			this.close();
		}
	}

	@Override
	public T findByPk(Long id) {
		try {
			Criteria criteria = this.getSession().createCriteria(this.clazz);
			return (T) criteria.add(Restrictions.idEq(id)).uniqueResult();
		} finally {
			this.close();
		}
	}

	@Override
	public List<T> findAll() {
		try {
			Criteria criteria = this.getSession().createCriteria(this.clazz);
			return criteria.list();
		} finally {
			this.close();
		}
	}

	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(this.clazz);
		SessionFactory factory = cfg.buildSessionFactory();
		return factory.openSession();
	}


	public void close() {
		if (this.getSession() != null && this.getSession().isOpen()) {
			this.getSession().close();
		}
	}
}
