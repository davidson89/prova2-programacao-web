package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.model.AssocPedidoItem;
import br.edu.ufabc.progWeb.prova2.model.Item;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

@Controller
public class ControllerPedido {

	@RequestMapping("adicionaPedido")
	public String adicionaPedido(@Valid Pedido pedido, BindingResult result) {
		BaseDAOFactory<Pedido> pedidoDAO = new BaseDAOFactory<Pedido>(Pedido.class);
		Date dataAtual = new Date(Calendar.getInstance().getTimeInMillis());
		if (pedido.getStatus()) {
			pedido.setDtFechamento(dataAtual);
		}
		if (pedido.getDtPedido() == null) {
			pedido.setDtPedido(dataAtual);
		}
		pedidoDAO.salve(pedido);
		return "redirect:novoPedido";
	}

	@RequestMapping("novoPedido")
	public String form(Model model) {
		BaseDAOFactory<Pedido> pedidoDAO = new BaseDAOFactory<Pedido>(Pedido.class);
		model.addAttribute("pedidos", pedidoDAO.findAll());
		return "pedido/pedido";
	}

	@RequestMapping("removePedido")
	public String remove(Pedido pedido) {
		BaseDAOFactory<Pedido> pedidoDAO = new BaseDAOFactory<Pedido>(Pedido.class);
		pedidoDAO.delete(pedido);
		return "redirect:novoPedido";
	}

	@RequestMapping("novoItemPedido")
	public String atualizaListaPedidos(Model model, Pedido pedido) {
		BaseDAOFactory<Item> itemDAO = new BaseDAOFactory<Item>(Item.class);
		BaseDAOFactory<Pedido> pedidoDAO = new BaseDAOFactory<Pedido>(Pedido.class);
		pedido = pedidoDAO.findByPk(pedido.getId());
		model.addAttribute("itens", itemDAO.findAll());
		model.addAttribute("pedido", pedido);
		return "assocPedidoItem/assocPedidoItem";
	}

	@RequestMapping("salvaAssocItemPedido")
	public String adicionaItem(Item item, Pedido pedido) {
		BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(AssocPedidoItem.class);
		AssocPedidoItem assocPedidoItem = new AssocPedidoItem();
		assocPedidoItem.setItem(item);
		assocPedidoItem.setPedido(pedido);
		// assocPedidoItem.setQtd(qtd);
		assocPedidoDAO.salve(assocPedidoItem);
		return "redirect:novoItemPedido";
	}

	@RequestMapping("removeAssocItemPedido")
	public String insereItem(AssocPedidoItem assocPedidoItem) {
		BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(AssocPedidoItem.class);
		assocPedidoDAO.delete(assocPedidoItem);
		return "redirect:novoItemPedido";
	}

}
