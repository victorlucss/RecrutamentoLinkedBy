package br.com.backend.Backend.Repository;

import br.com.backend.Backend.Model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Venda SET quantidade=:quantidade WHERE id_venda=:id_venda")
    public int darBaixa(@Param("quantidade") int quantidade, @Param("id_venda") Long id_venda);

    @Transactional
    @Modifying
    @Query("UPDATE Venda SET quantidade=:quantidade WHERE id_venda=:id_venda")
    public int darAlta(@Param("quantidade") int quantidade, @Param("id_venda") Long id_venda);

    @Query("SELECT V FROM Venda V WHERE id_produto=:id_produto")
    public Venda buscarPorIdProduto(@Param("id_produto") Long id_produto);

    @Transactional
    @Modifying
    @Query("DELETE FROM Venda WHERE id_venda=:id_venda")
    public void deletar(@Param("id_venda") Long id_venda);

}
