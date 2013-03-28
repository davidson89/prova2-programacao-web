package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufabc.progWeb.prova2.dao.AssocPedidoItemDAO;
import br.edu.ufabc.progWeb.prova2.dao.ItemDAO;
import br.edu.ufabc.progWeb.prova2.dao.PedidoDAO;
import br.edu.ufabc.progWeb.prova2.model.AssocPedidoItem;
import br.edu.ufabc.progWeb.prova2.model.Item;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

@Controller
public class ControllerAssocPedidoItem {

	private static AssocPedidoItemDAO assocPedidoDAO = new AssocPedidoItemDAO();

	private static PedidoDAO pedidoDAO = new PedidoDAO();

	private static ItemDAO itemDAO = new ItemDAO();

	private static String numeroPedido;

	private static String PADRAO_NUMERICO = "[0-9]+";

	// private static Pedido pedido;

	@RequestMapping("adicionaAssoc")
	public String adicionaPedido(AssocPedidoItem assocPedidoItem, BindingResult result, HttpServletRequest request,
			Model model) {
		numeroPedido = request.getParameter("numeroPedido_novo");

		String idItem = request.getParameter("id");
		String qtdeItem = request.getParameter("qtde");
		if (qtdeItem.equals("") || !qtdeItem.matches("[0-9]+")) {
			return "redirect:novoAssoc";
		}

		Pedido pedido = pedidoDAO.findByNumeroPedido(numeroPedido.trim());
		Item item = itemDAO.findByPk(new Long(idItem.trim()));
		AssocPedidoItem novoAssoc = new AssocPedidoItem();
		novoAssoc.setItem(item);
		novoAssoc.setPedido(pedido);
		novoAssoc.setQtd(new Long(qtdeItem));
		assocPedidoDAO.salve(novoAssoc);
		List<AssocPedidoItem> assocs = assocPedidoDAO.findByPedido(pedido);
		model.addAttribute("assocs", assocs);
		model.addAttribute("pedido", pedido);
		List<Item> itens = itemDAO.findAll();
		for (int i = 0; i < assocs.size(); i++) {
			for (Item item2 : itens) {
				if (assocs.get(i).getItem().equals(item2)) {
					itens.remove(item2);
				}
			}
		}
		model.addAttribute("itens", itens);
		return "assocPedidoItem/assocPedidoItem";
	}

	@RequestMapping("novoAssoc")
	public String form(Model model) {
		Pedido pedido = pedidoDAO.findByNumeroPedido(numeroPedido.trim());
		List<AssocPedidoItem> assocs = assocPedidoDAO.findByPedido(pedido);
		model.addAttribute("assocs", assocs);
		List<Item> itens = itemDAO.findAll();
		for (int i = 0; i < assocs.size(); i++) {
			for (Item item : itens) {
				if (assocs.get(i).getItem().equals(item)) {
					itens.remove(item);
				}
			}
		}
		model.addAttribute("itens", itens);
		return "assocPedidoItem/assocPedidoItem";
	}

	@RequestMapping("removeAssoc")
	public String remove(AssocPedidoItem assocPedidoItem, Model model) {
		assocPedidoDAO.delete(assocPedidoItem);
		// String numeroPedido = request.getParameter("numeroPedido_novo");
		Pedido pedido = pedidoDAO.findByNumeroPedido(numeroPedido.trim());
		model.addAttribute("pedido", pedido);
		return "redirect:novoAssoc";
	}
}
