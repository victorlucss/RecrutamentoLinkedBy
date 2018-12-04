package br.com.backend.Backend.Repository;

import br.com.backend.Backend.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT P FROM Produto P WHERE P.nome LIKE concat('%',:busca,'%')")
    public List<Produto> buscarProdutoPorNome(@Param("busca") String busca);

    @Query("SELECT P FROM Produto P WHERE P.nome=:nome")
    public Produto encontraPorNomeExato(@Param("nome") String nome);

    @Transactional
    @Modifying
    @Query("DELETE FROM Produto P WHERE P.id_produto=:id_produto")
    public void deletar(@Param("id_produto") Long id_produto);
}
