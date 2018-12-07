import { IProduto } from '../Interfaces/produto.interface';

export class Produto implements IProduto {
    id_produto: number;
    nome: string;
    descricao: string;
    imagem: string;
    preco: number;

    constructor(){ }

    getId_produto(): number {
        return this.id_produto;
    }
}