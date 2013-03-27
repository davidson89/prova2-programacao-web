package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.edu.ufabc.progWeb.prova2.model.Pedido;

public class PedidoDAO extends BaseDAOFactory<Pedido>{

	public PedidoDAO() {
		super(Pedido.class);
	}

	public Pedido findByNumeroPedido(String numeroPedido) {
		try {
			return (Pedido) this.getSession().createCriteria(Pedido.class)
					.add(Restrictions.eq("numeroPedido", numeroPedido)).uniqueResult();
		} finally {
			this.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> findBySolicitante(String nomeSolicitante) {
		try {
			return this.getSession().createCriteria(Pedido.class)
					.add(Restrictions.like("solicitante", nomeSolicitante, MatchMode.ANYWHERE)).list();
		} finally {
			this.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> findByEmail(String emailSolicitante) {
		try {
			return this.getSession().createCriteria(Pedido.class)
					.add(Restrictions.like("emailSolicitante", emailSolicitante, MatchMode.START)).list();
		} finally {
			this.close();
		}
	}
}