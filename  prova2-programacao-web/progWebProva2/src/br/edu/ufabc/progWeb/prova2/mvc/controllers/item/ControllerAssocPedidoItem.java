package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.dao.ItemDAO;
import br.edu.ufabc.progWeb.prova2.dao.PedidoDAO;
import br.edu.ufabc.progWeb.prova2.model.AssocPedidoItem;
import br.edu.ufabc.progWeb.prova2.model.Item;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

@Controller
public class ControllerAssocPedidoItem {

	private static BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(
			AssocPedidoItem.class);

	private static String PADRAO_NUMERICO = "[0-9]+";

	@RequestMapping(value = "/adicionaAssoc", method = RequestMethod.POST)
	public String adicionaPedido(BindingResult result, HttpServletRequest request) {

		PedidoDAO pedidoDAO = new PedidoDAO();
		Pedido pedido = pedidoDAO.findByPk(new Long(request.getParameter("id")));

		ItemDAO itemDAO = new ItemDAO();
		List<Item> itens = itemDAO.findAll();
		for (Item i : itens) {
			String qtdStr = request.getParameter("quantidade_" + i.getId());
			if (qtdStr.equals("")) {
				return "redirect:novoAssoc";
			}
			AssocPedidoItem assocPedidoItem = new AssocPedidoItem();
			assocPedidoItem.setItem(i);
			assocPedidoItem.setPedido(pedido);
			assocPedidoItem.setQtd(new Long(qtdStr));
			assocPedidoDAO.salve(assocPedidoItem);
		}
		return "redirect:novoAssoc";
	}

	@RequestMapping("novoAssoc")
	public String form(Model model) {
		BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(AssocPedidoItem.class);
		model.addAttribute("assoc", assocPedidoDAO.findAll());
		return "assocPedidoItem/assocPedidoItem";
	}

	@RequestMapping("removeAssoc")
	public String remove(AssocPedidoItem assocPedidoItem) {
		BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(AssocPedidoItem.class);
		assocPedidoDAO.delete(assocPedidoItem);
		return "redirect:novoAssoc";
	}
}
