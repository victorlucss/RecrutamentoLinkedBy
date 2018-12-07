package br.com.backend.Backend.Controller;

import br.com.backend.Backend.Model.ResponseSuccess;
import br.com.backend.Backend.Model.Venda;
import br.com.backend.Backend.Service.IMPL.VendaServiceIMPL;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="Vendas",description = "Dispõe e armazena os produtos vendidos pelo ecommerce")
@RequestMapping("/venda")

public class VendaController {

    @Autowired
    VendaServiceIMPL vendaService;

    @GetMapping
    public ResponseEntity<Venda> listarVendas(){
        List<Venda> vendas = vendaService.listar();

        return new ResponseEntity(new ResponseSuccess("Listando vendas!", 200, vendas), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity salvarVenda(@RequestBody Venda venda){
        vendaService.salvar(venda, venda.getProdutoOfertado().getId_produto());

        return new ResponseEntity(new ResponseSuccess("Venda inserida com sucesso!", 200), HttpStatus.OK);
    }

    @PostMapping("/dar-baixa")
    public ResponseEntity darBaixa(@RequestBody List<Venda> vendas){

        for(Venda venda: vendas){
            vendaService.darBaixa(venda.getQuantidade(), venda.getId_venda());
        }

        return new ResponseEntity(new ResponseSuccess("Foi dada baixa", 200), HttpStatus.OK);

    }

    @PostMapping("/dar-alta")
    public ResponseEntity darAlta(@RequestBody Venda venda){

        vendaService.darAlta(venda.getQuantidade(), venda.getId_venda());

        return new ResponseEntity(new ResponseSuccess("Foi dada alta", 200), HttpStatus.OK);

    }

}
