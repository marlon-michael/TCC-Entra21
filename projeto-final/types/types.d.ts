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
    sort(): Funcionarios;
    find(arg0: (h: Funcionarios) => boolean): Funcionarios;
    push(funcionario: Funcionarios): Funcionarios;
    findIndex(arg0: (h: Funcionarios) => boolean): Funcionarios;
    map(arg0: (h: Funcionarios) => string):  Funcionarios;
    length: number;
    filter(arg0: (h: Funcionarios) => boolean): Funcionarios;
    nome: string,
    sobrenome: string,
    cpf: string
}

export type Itens = {
    localizador: string,
    status: string,
    nomeRecebedor: string,
    localEntrega: string
}

export type ItensPessos = {

localizador: string;
status:string;
localEntrega:string;
nomeRecebedor:string;
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

// export type Funcionario = {
//  FuncionarioDTO supervisor:;
//  List<EntregaDTO> entrega:;
//  EmpresaDTO empresa:;
// }