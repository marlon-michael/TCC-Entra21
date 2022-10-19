export type User = {
    id: number;
    username: string;
    password: string;
    authdata?: string;
}

export type Cadastro = {
    nome : string,
    sobrenome : string,
    telefone: string,
    cpf : string,
    login : string,
    senha : string,
    authdata?: string;
}

export type Funcionarios = {
    nome: string,
    sobrenome: string
}

export type Itens = {
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string
}

