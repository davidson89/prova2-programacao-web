package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.model.Pedido;

@Controller
public class ControllerPedido {

	@RequestMapping("adicionaPedido")
	public String adicionaItem(@Valid Pedido pedido, BindingResult result) {
		BaseDAOFactory<Pedido> pedidoDAO = new BaseDAOFactory<Pedido>(Pedido.class);
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
}
