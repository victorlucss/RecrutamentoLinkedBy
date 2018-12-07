package br.com.backend.Backend.Service.IMPL;

import br.com.backend.Backend.Exception.EcommerceException;
import br.com.backend.Backend.Model.Produto;
import br.com.backend.Backend.Model.Venda;
import br.com.backend.Backend.Repository.VendaRepository;
import br.com.backend.Backend.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaServiceIMPL implements VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ProdutoServiceIMPL produtoService;

    @Override
    public List<Venda> listar() {
        List<Venda> vendas = vendaRepository.findAll();

        if(vendas.isEmpty()) throw new EcommerceException("Nenhuma venda disponível!", 404);

        return vendas;
    }

    @Override
    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new EcommerceException("Nenhuma venda com este id!", 404));
    }

    @Override
    public Venda buscarPorIdProduto(Long id_produto){
        Venda venda = vendaRepository.buscarPorIdProduto(id_produto);
        return venda;
    }

    @Override
    public Venda salvar(Venda venda, Long id_produto) {
        if(this.buscarPorIdProduto(id_produto) != null) throw new EcommerceException("Já existe uma venda cadastrada com este produto!", 209);

        Produto produto = produtoService.buscarPorId(id_produto);

        venda.setProdutoOfertado(produto);

        return vendaRepository.save(venda);
    }

    @Override
    public void darBaixa(int quantidade, Long id_venda) {
        Venda venda = this.buscarPorId(id_venda);
        if(venda.getQuantidade() - quantidade < 0) throw new EcommerceException("Você não pode dar baixa em mais produtos do que existem!", 401);
        if(venda.getQuantidade() - quantidade == 0) this.deletar(id_venda);
        vendaRepository.darBaixa(venda.getQuantidade() - quantidade, id_venda);
    }

    @Override
    public void darAlta(int quantidade, Long id_venda) {
        Venda venda = this.buscarPorId(id_venda);

        vendaRepository.darAlta(venda.getQuantidade() + quantidade, id_venda);
    }

    @Override
    public void deletar(Long id_venda) {
        this.buscarPorId(id_venda);
        vendaRepository.deletar(id_venda);
    }
}
