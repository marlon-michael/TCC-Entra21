export type User = {
    id: number;
    username: string;
    password: string;
    role: string;
    authdata?: string;
}

export type Cadastro = {
    nome : string | null | undefined,
    sobrenome : string | null| undefined,
    telefone: string | null| undefined,
    cpf : string | null| undefined,
    login : string | null| undefined,
    senha : string | null| undefined,
    authdata?: string;
}

export type Funcionarios = {
    id: number;
    nome: string,
    sobrenome: string,
    cpf: string,
    telefone: string
}

export type Itens = {
    id: number;
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string,
    pessoaItem: Pessoa;
    funcionario: Funcionario;
  
}
export type Item = {
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string,
    pessoaItem:  Pessoa;
    funcionario:  Funcionario;
}

export type ItensPessoas = {

localizador: string;
status:string;
localEntrega:string;
nomeRecebedor:string;
pessoaItem:  Pessoa;
}

export type Pessoa = {
    id: number;
     nome:string
     sobrenome: string;
     telefone: string;
     cpf: string;
     login: string;
     senha: string;
}

export type Funcionario = {
 supervisor: Pessoa;
 entrega: Entrega[];
  empresa: Empresa;
}

export type Entrega = {
    id: number;
     tipoEntrega: string;
     entregador: Pessoa;
     entregaTrecho: EntregaTrecho;
     itens: Itens;
}

export type Empresa = {
    id: number;
     cnpj: string;
     razaoSocial:string;
    gerente: Pessoa;
    carros: Carro[];
     funcionarios: Pessoa[];
}

export type Carro = {
    id: number;
      tipoCarro: string;
      placa: string;
      empresaCarro: Empresa;
}

export type EntregaTrecho = {
    id: number;
     Completo: boolean;
     carro: Carro;
     entrega: Entrega;
     trecho: Trecho;
}

export type Trecho = {
    id: number;
     localInicio: string;
     localFim: string;
}
