package br.com.backend.Backend.Controller;

import br.com.backend.Backend.Model.Produto;
import br.com.backend.Backend.Model.ResponseSuccess;
import br.com.backend.Backend.Service.IMPL.ProdutoServiceIMPL;
import br.com.backend.Backend.Service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="Produto", description = "Ações a serem realizadas em produtos.")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoServiceIMPL produtoService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Produto produto){
        return new ResponseEntity(produtoService.salvar(produto), HttpStatus.OK);
    }

    @PutMapping("{idProduto}")
    public ResponseEntity atualizar(@RequestBody Produto produto, @PathVariable("idProduto") Long id_produto){
        return new ResponseEntity(produtoService.atualizar(produto, id_produto), HttpStatus.OK);
    }

    @GetMapping("{idProduto}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable("idProduto") Long id){
        Produto produto = produtoService.buscarPorId(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Produto> listar(){
        return new ResponseEntity(new ResponseSuccess("Listando produtos", 200, produtoService.listar()), HttpStatus.OK);
    }

    @DeleteMapping("{idProduto}")
    public ResponseEntity deletar(@PathVariable("idProduto") Long id_produto){
        produtoService.deletar(id_produto);
        return new ResponseEntity(new ResponseSuccess("Produto deletado com sucesso!", 200), HttpStatus.OK);
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity buscarPorNome(@PathVariable("nome") String busca){
        List<Produto> produtos = produtoService.buscarPorNome(busca);
        return new ResponseEntity(new ResponseSuccess("Listando produtos com "+busca, 200, produtos), HttpStatus.OK);

    }


}
