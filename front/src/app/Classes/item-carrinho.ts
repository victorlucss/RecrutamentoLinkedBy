import { Produto } from './produto';

export class ItemCarrinho {
    private produto: Produto;
    private quantidade: number;
    private id_venda: number;

    constructor(produto: Produto, quantidade: number) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    getQuantidade(): number {
        return this.quantidade;
    }

    setQuantidade(quantidade: number): void {
        this.quantidade = quantidade;
    }

    getId_produto(): number {
        return this.produto.getId_produto();
    }
}