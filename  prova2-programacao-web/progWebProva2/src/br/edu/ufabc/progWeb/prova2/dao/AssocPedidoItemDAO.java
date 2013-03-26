package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.edu.ufabc.progWeb.prova2.model.AssocPedidoItem;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

public class AssocPedidoItemDAO extends BaseDAOFactory<AssocPedidoItem> {

	public AssocPedidoItemDAO() {
		super(AssocPedidoItem.class);
	}

	public List<AssocPedidoItem> fingByPedido(Pedido pedido) {
		try {
			Criteria criteria = this.getSession().createCriteria(Pedido.class);
			criteria.add(Restrictions.eq("pedido", pedido));
			return criteria.list();
		} finally {
			close();
		}
	}
}
