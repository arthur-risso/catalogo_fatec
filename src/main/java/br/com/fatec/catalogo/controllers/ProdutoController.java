package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository; // Importação necessária
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/novo")
    public String exibirFormulario(Model model){
        model.addAttribute("produto", new ProdutoModel());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "cadastro-produto";
    }

    @GetMapping("/editar/{id}")
    public String exibirEdicao(@PathVariable long id, Model model){
        model.addAttribute("produto", service.buscarPorId(id));
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "editar-produto";
    }

    @PostMapping("/salvar")
    public String salvarProduto(
            @Valid @ModelAttribute("produto") ProdutoModel produto,
            BindingResult result,
            Model model){

        if (result.hasErrors()){
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "cadastro-produto";
        }

        try {
            service.salvar(produto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("erro", e.getMessage());
            return "cadastro-produto";
        }

        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id){
        service.excluir(id);
        return "redirect:/produtos";
    }

    @GetMapping
    public String listarProdutos(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Long categoriaId,
            Model model) {

        // Sempre envia as categorias para o dropdown de filtro aparecer
        model.addAttribute("categorias", categoriaRepository.findAll());

        if (categoriaId != null) {
            // Filtra por categoria
            model.addAttribute("produtos", service.buscarPorCategoria(categoriaId));
        } else if (busca != null && !busca.isEmpty()) {
            // Filtra por nome
            model.addAttribute("produtos", service.buscarPorNome(busca));
        } else {
            // Lista tudo se não houver filtro
            model.addAttribute("produtos", service.listarTodos());
        }

        model.addAttribute("busca", busca);
        return "lista-produtos";
    }
}
