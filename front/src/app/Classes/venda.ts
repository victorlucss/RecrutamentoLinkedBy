import { Produto } from './produto';

export class Venda {
    id_venda: number;
    produtoOfertado: Produto;
    quantidade: number;

    constructor() { }
}