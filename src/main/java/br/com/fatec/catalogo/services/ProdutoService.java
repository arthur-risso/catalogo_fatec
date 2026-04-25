package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public List<ProdutoModel> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel buscarPorId(long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }
    public List<ProdutoModel> buscarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }

    @Transactional
    public void salvar(ProdutoModel produto){
        var existente = repository.findByNomeIgnoreCase(produto.getNome());

        if (existente.isPresent() &&
                !existente.get().getIdProduto().equals(produto.getIdProduto())) {

            throw new IllegalArgumentException("Já existe um produto com esse nome.");
        }

        repository.save(produto);
    }

    @Transactional
    public void excluir(long id){
        repository.deleteById(id);
    }

//    @Bean
  //  public UserDetailsService userDetailsService() {
    //    //usuario comum
      //  UserDetails user = User.builder().username("aluno").password("{noop}12345").roles("USER").build();

        //Admin
//        UserDetails admin = User.builder().username("admin").password("{noop}12345").roles("ADMIN", "USER").build();

  //      return new InMemoryUserDetailsManager(user, admin);
    //}
}
