package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
public class ControllerPedido {

	private static PedidoDAO pedidoDAO = new PedidoDAO();

	private static ItemDAO itemDAO = new ItemDAO();

	private static BaseDAOFactory<AssocPedidoItem> assocPedidoDAO = new BaseDAOFactory<AssocPedidoItem>(
			AssocPedidoItem.class);

	private static String PADRAO_NUMERICO = "[0-9]+";

	@RequestMapping("adicionaPedido")
	public String adicionaPedido(@Valid Pedido pedido, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pedidos", pedidoDAO.findAll());
			return "pedido/pedido";
		}
		Date dataAtual = new Date(Calendar.getInstance().getTimeInMillis());
		if (pedido.getStatus()) {
			pedido.setDtFechamento(dataAtual);
		}
		Pedido solicitacao = null;
		solicitacao = pedidoDAO.findByPk(pedido.getId());
		if (solicitacao == null) {
			pedido.setDtPedido(dataAtual);
		} else {
			pedido.setDtPedido(solicitacao.getDtPedido());
		}
		pedidoDAO.salve(pedido);
		return "redirect:novoPedido";
	}

	@RequestMapping("novoPedido")
	public String form(Model model) {
		model.addAttribute("pedidos", pedidoDAO.findAll());
		return "pedido/pedido";
	}

	@RequestMapping("removePedido")
	public String remove(Pedido pedido) {
		pedidoDAO.delete(pedido);
		return "redirect:novoPedido";
	}

	@RequestMapping("novoItemPedido")
	public String atualizaListaPedidos(Model model, Pedido pedido) {
		pedido = pedidoDAO.findByPk(pedido.getId());
		model.addAttribute("itens", itemDAO.findAll());
		model.addAttribute("pedido", pedido);
		return "assocPedidoItem/assocPedidoItem";
	}

	@RequestMapping("salvaAssocItemPedido")
	public String adicionaItem(Item item, Pedido pedido) {
		AssocPedidoItem assocPedidoItem = new AssocPedidoItem();
		assocPedidoItem.setItem(item);
		assocPedidoItem.setPedido(pedido);
		// assocPedidoItem.setQtd(qtd);
		assocPedidoDAO.salve(assocPedidoItem);
		return "redirect:novoItemPedido";
	}

	@RequestMapping("removeAssocItemPedido")
	public String insereItem(AssocPedidoItem assocPedidoItem) {
		assocPedidoDAO.delete(assocPedidoItem);
		return "redirect:novoItemPedido";
	}

	@RequestMapping(value = "/buscaPedido", method = RequestMethod.POST)
	public String form2(Model model, HttpServletRequest request) {
		String acao = request.getParameter("tipoBusca");
		String valorObjetivo = request.getParameter("valorObjetivo");
		if (valorObjetivo.equals("")) {
			return "redirect:novoPedido";
		}
		if (acao.equals("id")) {
			if (valorObjetivo.matches(PADRAO_NUMERICO)) {
				Pedido pedidoSolicitado = pedidoDAO.findByPk(new Long(valorObjetivo));
				List<Pedido> pedidos = new ArrayList<Pedido>();
				pedidos.add(pedidoSolicitado);
				model.addAttribute("pedidos", pedidos);
				return "pedido/pedido";
			} else {
				return "redirect:novoPedido";
			}
		} else if (acao.equals("numeroPedido")) {
			if (valorObjetivo.matches(PADRAO_NUMERICO)) {
				Pedido pedidoSolicitado = pedidoDAO.findByNumeroPedido(valorObjetivo);
				List<Pedido> pedidos = new ArrayList<Pedido>();
				pedidos.add(pedidoSolicitado);
				model.addAttribute("pedidos", pedidos);
				return "pedido/pedido";
			} else {
				return "redirect:novoPedido";
			}
		} else if (acao.equals("solicitante")) {
			List<Pedido> pedidos = pedidoDAO.findBySolicitante(valorObjetivo);
			if (pedidos.size() == 0) {
				return "redirect:novoPedido";
			} else {
				model.addAttribute("pedidos", pedidos);
				return "pedido/pedido";
			}
		} else if (acao.equals("emailSolicitante")) {
			List<Pedido> pedidos = pedidoDAO.findByEmail(valorObjetivo);
			if (pedidos.size() == 0) {
				return "redirect:novoPedido";
			} else {
				model.addAttribute("pedidos", pedidos);
				return "pedido/pedido";
			}
		} else if (acao.equals("valorTotal")) {
			List<Item> lista = new ArrayList<Item>();
			if (valorObjetivo.matches("[0-9]+|[0-9]+[.][0-9]+")) {
				lista = itemDAO.findByValor(new Double(valorObjetivo));
			} else if (valorObjetivo.matches("[0-9]+[,][0-9]+")) {
				String[] valorVetor = new String[2];
				valorVetor = valorObjetivo.split(",");
				String valorDoubleStr = valorVetor[0] + "." + valorVetor[1];
				lista = itemDAO.findByValor(new Double(valorDoubleStr));
			}
			if (lista.size() == 0) {
				return "redirect:novoItem";
			} else {
				model.addAttribute("itens", lista);
				return "item/formulario";
			}
		}
		return "item/formulario";
	}
}
