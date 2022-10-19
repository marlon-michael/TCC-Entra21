export type User = {
    id: number;
    username: string;
    password: string;
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
    nome: string,
    sobrenome: string
}

export type Itens = {
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string
}

