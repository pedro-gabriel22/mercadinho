package br.com.projetoSA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import br.com.projetoSA.model.Produto;
import br.com.projetoSA.repository.ProdutoRepository;
import br.com.projetoSA.service.ProdutoService;

@Controller
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/produto/list")
	public String listProduto(Model model) {
		model.addAttribute("produtos", produtoRepository.findAll(Sort.by("nome")));
		return "produto/list";
	}
	
	@GetMapping("/produto/view/{id}")
	public String viewProduto(@PathVariable long id, Model model) {
		model.addAttribute("produto", produtoRepository.findById(id));
		return "/produto/view";
	}

	@GetMapping("/produto/add")
	public String addProduto() {
		return "produto/add";
	}

	@PostMapping("/produto/save")
	public String saveProduto(Produto produto) {

		return produtoService.saveAddProduto(produto);
	}

	@GetMapping("/produto/edit")
	public String editProduto() {
		return "produto/edit";
	}
	
	@PutMapping("/produto/save/{id}")
	public String saveEditProduto(@PathVariable Long id, Produto produto) {
		
		return produtoService.saveEditProduto(id, produto);
	}
	
	@DeleteMapping("/produto/delete/{id}")
	public String deleteProduto(@PathVariable Long id) {

		return produtoService.deleteProduto(id);
	}
}
