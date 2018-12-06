package br.com.backend.Backend.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venda;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    private Produto produtoOfertado;

    private int quantidade;

    public Venda() {
    }

    public Venda(int quantidade) {
        this.quantidade = quantidade;
    }

    public Venda(Produto produtoOfertado, int quantidade) {
        this.produtoOfertado = produtoOfertado;
        this.quantidade = quantidade;
    }

    public Long getId_venda() {
        return id_venda;
    }

    public void setId_venda(Long id_venda) {
        this.id_venda = id_venda;
    }

    public Produto getProdutoOfertado() {
        return produtoOfertado;
    }

    public void setProdutoOfertado(Produto produtoOfertado) {
        this.produtoOfertado = produtoOfertado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
