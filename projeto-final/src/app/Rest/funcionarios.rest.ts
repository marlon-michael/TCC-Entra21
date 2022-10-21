import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Funcionarios } from 'types/types';

@Injectable({ providedIn: 'root' })
export class FuncionarioRestController {
    router: any;
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Funcionarios[]>(`/funcionario`);
    }
    getbycnpj() {
        return this.http.get<Funcionarios[]>(`/funcionario/empresa/{cnpj}`);
    }
    // onEdit(){
        // this.router.navigate(controls:['edit', form.cpf], extras: {relativeTo: this.route})
        //   }
    // "/empresa/{cnpj}"
    
  
}