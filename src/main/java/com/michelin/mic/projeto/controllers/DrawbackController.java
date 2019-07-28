package com.michelin.mic.projeto.controllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.michelin.mic.projeto.services.DrawbackService;
import com.michelin.mic.projeto.vo.DrawbackVO;

@Controller
public class DrawbackController {
	
	@Autowired
	private DrawbackService service;
	
	@RequestMapping("/form")
	public String iniciarPagina(Model model,@RequestParam(required = false)  String error, @RequestParam(required = false) String message) {
		model.addAttribute("actualDate", new Date());
		model.addAttribute("registro", new DrawbackVO());
		ArrayList<DrawbackVO> registros = service.findAll();
		model.addAttribute("registros", registros);
		model.addAttribute("error", error);
		model.addAttribute("message", message);

		return "form";
	}
	
	@PostMapping("/form-add")
	public ModelAndView addItem(Authentication authentication, Model model, @ModelAttribute DrawbackVO drawback) {
        ModelAndView modelAndView = new ModelAndView("redirect:/form");
		drawback.setDataModificacao(new Date());
		drawback.setUsuario(authentication.getName());
		
		if(service.insert(drawback) == -1) {
			modelAndView.addObject("error", "Falha ao Inserir! NCM já existe em outro ATO vigente.");
		} else {
			modelAndView.addObject("message", "Inserido com sucesso!");
		}
		
        return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=update")
	public ModelAndView  updateItem(Authentication authentication,Model model, @ModelAttribute(value="registro") DrawbackVO registro) {
        ModelAndView modelAndView = new ModelAndView("redirect:/form");
		registro.setDataModificacao(new Date());
		registro.setUsuario(authentication.getName());
		switch (service.update(registro)) {
			case -1:
				modelAndView.addObject("error", "Falha ao alterar! Data final inválida.");
			break;
			case -2:
				modelAndView.addObject("error", "Falha ao alterar! NCM já existe em outro ATO vigente.");
			break;
	
			default:
				modelAndView.addObject("message", "Alterado com sucesso!");
			break;
		}
        return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=delete")
	public ModelAndView deleteItem(Model model, @ModelAttribute(value="registro") DrawbackVO registro) {
		
        ModelAndView modelAndView = new ModelAndView("redirect:/form");
		service.delete(registro);
		modelAndView.addObject("message", "Excluído com sucesso!");
        return modelAndView;

	}
	
}
