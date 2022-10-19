export type User = {
    id: number;
    login: string;
    senha: string;
    authdata?: string;
}

export type Cadastro = {
    nome : string,
    sobrenome : string,
    telefone: string,
    cpf : string,
    login : string,
    senha : string,
}

export type Item = {
    localizador: string,
    status: string,
    localEntrega: string,
    nomeRecebedor: string
}

