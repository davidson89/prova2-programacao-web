package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.dao.ItemDAO;
import br.edu.ufabc.progWeb.prova2.model.Item;

@Controller
public class ControllerItem {

	private static ItemDAO itemDAO = new ItemDAO();

	private static String PADRAO_NUMERICO = "[0-9]+";

	@RequestMapping("adicionaItem")
	public String adicionaItem(@Valid Item item, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors() && !result.hasFieldErrors("preco")) {
			BaseDAOFactory<Item> itemDAO = new BaseDAOFactory<Item>(Item.class);
			model.addAttribute("itens", itemDAO.findAll());
			return "item/formulario";
		}
		String valor = request.getParameter("preco");
		if (valor.matches("[0-9]+|[0-9]+[.][0-9]+")) {
			item.setPreco(new Double(valor));
		} else if (valor.matches("[0-9]+[,][0-9]+")) {
			String[] valorVetor = new String[2];
			valorVetor = valor.split(",");
			String valorDoubleStr = valorVetor[0] + "." + valorVetor[1];
			item.setPreco(new Double(valorDoubleStr));
		}
		if (item.getPreco() == null) {
			result.addError(new ObjectError("preco", "Preço possui valor incorreto"));
			return "redirect:novoItem";
		}
		itemDAO.salve(item);
		return "redirect:novoItem";
	}

	@RequestMapping("novoItem")
	public String form(Model model) {
		model.addAttribute("itens", itemDAO.findAll());
		return "item/formulario";
	}

	@RequestMapping("remove")
	public String remove(Item item) {
		itemDAO.delete(item);
		return "redirect:novoItem";
	}

	@RequestMapping(value = "/buscaItem", method = RequestMethod.POST)
	public String form2(Model model, HttpServletRequest request) {
		String acao = request.getParameter("tipoBusca");
		String valorObjetivo = request.getParameter("valorObjetivo");
		if (acao.equals("id")) {
			if (valorObjetivo.matches(PADRAO_NUMERICO)) {
				Item itemSolicitado = itemDAO.findByPk(new Long(valorObjetivo));
				List<Item> itens = new ArrayList<Item>();
				itens.add(itemSolicitado);
				model.addAttribute("itens", itens);
				return "item/formulario";
			} else {
				return "redirect:novoItem";
			}
		} else if (acao.equals("descricao")) {
			List<Item> lista = itemDAO.findByDescricao(valorObjetivo);
			if (lista.size() == 0) {
				return "redirect:novoItem";
			} else {
				model.addAttribute("itens", lista);
				return "item/formulario";
			}
		} else if (acao.equals("valor")) {
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
