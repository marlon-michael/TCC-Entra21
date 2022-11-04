import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Funcionario, Funcionarios } from 'types/types';
import { BehaviorSubject, map, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FuncionarioRestController {
    private userSubject: BehaviorSubject<Funcionarios | null>;
    public colabora: Observable<Funcionarios | null>;
  
    router: any;
    constructor(private http: HttpClient) {
    this.userSubject = new BehaviorSubject<Funcionarios | null>(localStorage.getItem( 'user') != null ? JSON.parse( localStorage.getItem('user')!): null);
    this.colabora = this.userSubject.asObservable(); }

    getAll() {
        return this.http.get<Funcionarios[]>(`/funcionario`);
    }
    getbycnpj() {
        return this.http.get<Funcionario[]>(`/funcionario/empresa/{cnpj}`);
    }
    // onEdit(){
        // this.router.navigate(controls:['edit', form.cpf], extras: {relativeTo: this.route})
        //   }
    // "/empresa/{cnpj}"
    colaborador( nome: string | null,sobrenome: string | null,cpf: string | null,telefone: string | null,) {

        return this.http.post<any>(`/funcionario/addfuncionario`, { nome,sobrenome, cpf, telefone})
                .pipe(map((colabora: Funcionarios | null) => {
                  localStorage.setItem('user', JSON.stringify(colabora));
                  this.userSubject.next(colabora);
                  return colabora;
    
              }));
            }
  
}