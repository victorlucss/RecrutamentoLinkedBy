package br.com.backend.Backend.Service.IMPL;

import br.com.backend.Backend.Exception.EcommerceException;
import br.com.backend.Backend.Model.Produto;
import br.com.backend.Backend.Repository.ProdutoRepository;
import br.com.backend.Backend.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceIMPL implements ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listar() {
        List<Produto> produtos = produtoRepository.findAll();

        if(produtos.isEmpty()) throw new EcommerceException("Nenhum produto cadastrado!", 204);

    return produtos;
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EcommerceException("Nenhum produto com este id encontrado!", 404));
    }

    @Override
    public List<Produto> buscarPorNome(String busca) {
        List<Produto> produtos = produtoRepository.buscarProdutoPorNome(busca);

        if(produtos.isEmpty()) throw new EcommerceException("Não há produtos condizentes com esta busca!", 204);

        return produtos;
    }

    @Override
    public Produto encontraPorNomeExato(String nome){
        Produto produto = produtoRepository.encontraPorNomeExato(nome);

        return produto;
    }

    @Override
    public Produto salvar(Produto p) {
        if(this.encontraPorNomeExato(p.getNome()) == null){
            return produtoRepository.save(p);
        }else throw new EcommerceException("Já existe um produto com este nome!", 409);

    }

    @Override
    public Produto atualizar(Produto p, Long id_produto) {
        this.buscarPorId(id_produto);
        p.setId_produto(id_produto);
        return produtoRepository.save(p);

    }

    @Override
    public void deletar(Long id_produto) {
        produtoRepository.deletar(id_produto);
    }
}
