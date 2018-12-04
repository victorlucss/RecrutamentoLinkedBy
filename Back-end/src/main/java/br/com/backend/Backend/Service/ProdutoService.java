package br.com.backend.Backend.Service;

import br.com.backend.Backend.Model.Produto;

import java.util.List;

public interface ProdutoService {
    public List<Produto> listar();

    public Produto buscarPorId(Long id);

    public List<Produto> buscarPorNome(String busca);

    public Produto encontraPorNomeExato(String nome);

    public Produto salvar(Produto produto);

    public void deletar(Long id_produto);
}
