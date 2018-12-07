export class Compra {
    private quantidade: number;
    private produtoOfertado: number;
    private id_venda: number;

    constructor(quantidade: number, produtoOfertado: number, id_venda: number) {
        this.quantidade = quantidade;
        this.produtoOfertado = produtoOfertado;
        this.id_venda = id_venda;
    }
}