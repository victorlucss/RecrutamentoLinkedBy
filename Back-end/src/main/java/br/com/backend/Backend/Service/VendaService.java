package br.com.backend.Backend.Service;

import br.com.backend.Backend.Model.Venda;

import java.util.List;

public interface VendaService {
    public List<Venda> listar();

    public Venda buscarPorId(Long id);

    public Venda buscarPorIdProduto(Long id_produto);

    public Venda salvar(Venda venda, Long id_produto);

    public void darBaixa(int quantidade, Long id_venda);

    public void darAlta(int quantidade, Long id_venda);

    public void deletar(Long id_venda);
}
