package br.edu.ufabc.progWeb.prova2.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.model.AssocPedidoItem;
import br.edu.ufabc.progWeb.prova2.model.Item;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

public class GeraTabelas {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Item.class);
		cfg.addAnnotatedClass(Pedido.class);
		cfg.addAnnotatedClass(AssocPedidoItem.class);
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
}
