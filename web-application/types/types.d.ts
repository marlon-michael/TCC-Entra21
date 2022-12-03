
export type User = {
    idPessoa: number;
    cpf:string;
    telefone:string;
    nome: string;
    sobrenome: string;
    username: string;
    password: string;
    role: string;
    authdata?: string;
}

export type Funcionarios = {
    nome: string,
    sobrenome: string,
    cpf: string,
    telefone: string
}

export type Item = {
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string,
    pessoaItem:  Pessoa;
}

export type Pessoa = {
    nome:string
    sobrenome: string;
    telefone: string;
    cpf: string;
    login: string;
    senha: string;
}

export type Entrega = {
    idEntrega: number;
    tipoEntrega: string;
    entregador: Pessoa;
    entregaTrecho: EntregaTrecho[];
    itens: Item[];
}

export type EntregaSingleLine = {
    id: number;
    entregador: Pessoa;
    tipoEntrega: string;
    entregaTrecho: EntregaTrecho;
}

export type Empresa = {
    cnpj: string;
    razaoSocial:string;
    gerente: Pessoa;
    carros: Carro[];
    funcionarios: Pessoa[];
}

export type Carro = {
    tipoCarro: string;
    placa: string;
    empresaCarro: Empresa;
}

export type EntregaTrecho = {
    completo: boolean;
    carro: Carro;
    entrega: Entrega;
    trecho: Trecho;
    dataInicio: string;
    dataFim: string;
}

export type Trecho = {
    localInicio: string;
    localFim: string;
}
