import { Component, OnInit } from '@angular/core';
import { VendaService } from '../services/venda/venda.service';

import { Produto } from '../Classes/produto';
import { ItemCarrinho } from '../Classes/item-carrinho';
import { Venda } from '../Classes/venda';
import { Compra } from '../Classes/compra';

@Component({
	selector: 'app-principal',
	templateUrl: './principal.component.html',
	styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {

	public mostrarCarrinho = false;
	public vendas: Map<number, Venda> = new Map();
	public carrinho: Map<number, ItemCarrinho> = new Map();
	public valorCarrinho: number = 0;

	constructor(private httpService: VendaService) { }

	ngOnInit() {
		this.getVendas();
	}

	public trataPreco(preco: number): string {
		return `R$${preco.toString().replace('.', ',')}`;
	}

	public adicionarCarrinhoPrincipal(item: object): void {
		let produto: Produto = Object.assign(new Produto(), item);

		if(!this.carrinho.has(produto.getId_produto())){
			this.carrinho.set(produto.getId_produto(), new ItemCarrinho(produto, 1));
			this.valorCarrinho += produto.preco;
			this.mostrarCarrinho = true;
		}else {
			alert('Este produto já está no carrinho!');
		}
	}

	public adicionarCarrinho(item: object): void {
		let produto: Produto = Object.assign(new Produto(), item);

		if (this.carrinho.has(produto.getId_produto())) {
			let item_carrinho: ItemCarrinho = this.carrinho.get(produto.getId_produto());
			if (this.quantidadeMaxItem(item_carrinho)) {
				alert('Você já adicionou todos produtos iguais a este disponíveis no estoque!')
			} else {
				item_carrinho.setQuantidade(item_carrinho.getQuantidade() + 1);
				this.valorCarrinho += produto.preco;
			}
		}
	}

	public removerCarrinho(item: object): void {
		let produto: Produto = Object.assign(new Produto(), item);

		if(this.carrinho.has(produto.getId_produto())){
			let item_carrinho: ItemCarrinho = this.carrinho.get(produto.getId_produto());

			if (item_carrinho.getQuantidade() > 0 && item_carrinho.getQuantidade() != 1) {
				item_carrinho.setQuantidade(item_carrinho.getQuantidade() - 1)
			} else {
				this.carrinho.delete(produto.getId_produto());
			}

			this.valorCarrinho -= produto.preco;
		} else {
			alert('Este produto não existe no carrinho!');
		}
	}

	public getQuantidadeCarrinho(item: object): number{
		let produto: Produto = Object.assign(new Produto(), item);

		if(this.carrinho.has(produto.getId_produto())){
			return this.carrinho.get(produto.getId_produto()).getQuantidade();
		} else {
			return 0;
		}
	}

	public estaNoCarrinho(id_produto: number): boolean {
		return this.carrinho.has(id_produto);
	}


	public gerarCompraFinal(): void {
		const compraFinal: Compra[] = new Array();

		this.carrinho.forEach((item_carrinho: ItemCarrinho) => {
			compraFinal.push(new Compra(item_carrinho.getQuantidade(), item_carrinho.getId_produto(), this.vendas.get(item_carrinho.getId_produto()).id_venda ));
		});

		this.httpService.darBaixa(compraFinal).then(res => {
			alert(res.message);
			this.limparSessao();
		})
	}


	protected getVendas(): void{
		this.httpService.getVendas().subscribe(data => {
			let vendas = data['data'];
			vendas.map((val) => {
				let venda: Venda = Object.assign(new Venda(), val);
				this.vendas.set(venda.produtoOfertado.id_produto, venda);
			});
		});
	}

	protected limparSessao():void {
		this.mostrarCarrinho = false;
		this.carrinho.clear();
		this.getVendas();
	}

	protected quantidadeMaxItem(item_carrinho: ItemCarrinho): boolean {
		if (this.vendas.has(item_carrinho.getId_produto()) && this.vendas.get(item_carrinho.getId_produto()).quantidade <= item_carrinho.getQuantidade()) {
			return true;
		}

		return false;
	}

}
