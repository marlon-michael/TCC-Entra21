export type User = {
    id: number;
    login: string;
    password: string;
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