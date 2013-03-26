package br.edu.ufabc.progWeb.prova2.mvc.controllers.item;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufabc.progWeb.prova2.dao.BaseDAOFactory;
import br.edu.ufabc.progWeb.prova2.model.Item;

@Controller
public class ControllerItem {
	
	@RequestMapping("adicionaItem")
	public String adicionaItem(@Valid Item item, BindingResult result) {
		BaseDAOFactory<Item> itemDAO = new BaseDAOFactory<Item>(Item.class);
		itemDAO.salve(item);
		return "redirect:novoItem";
	}
	
	@RequestMapping("novoItem")
	public String form(Model model) {
		BaseDAOFactory<Item> itemDAO = new BaseDAOFactory<Item>(Item.class);
		model.addAttribute("itens", itemDAO.findAll());
		return "item/formulario";
	}
	
	@RequestMapping("remove")
	public String remove(Item item) {
		BaseDAOFactory<Item> itemDAO = new BaseDAOFactory<Item>(Item.class);
		itemDAO.delete(item);
		return "redirect:novoItem";
	}

}
