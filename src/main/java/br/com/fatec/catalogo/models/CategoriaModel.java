package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias") // Define o nome da tabela no PostgreSQL
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O Postgres cuidará do auto-incremento
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 3, message = "O nome da categoria deve ter no mínimo 3 caracteres.")
    private String nome;

    // Relacionamento inverso (Opcional, mas útil para listar produtos de uma categoria)
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutoModel> produtos;

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
