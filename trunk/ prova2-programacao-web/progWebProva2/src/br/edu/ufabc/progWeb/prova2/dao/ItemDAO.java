package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.edu.ufabc.progWeb.prova2.model.Item;

public class ItemDAO extends BaseDAOFactory<Item> {

	public ItemDAO() {
		super(Item.class);
	}

	public List<Item> findByDescricao(String descricao) {
		try {
			return this.getSession().createCriteria(Item.class)
					.add(Restrictions.like("descricao", descricao, MatchMode.START)).list();

		} finally {
			this.close();
		}
	}

	public List<Item> findByValor(String preco) {
		try {
			return this.getSession().createCriteria(Item.class).add(Restrictions.like("preco", preco, MatchMode.START))
					.list();

		} finally {
			this.close();
		}
	}
}
