import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Cadastro } from 'types/types';

@Injectable({
  providedIn: 'root'
})
export class cadastroFuncionarioService {
  private userSubject: BehaviorSubject<Cadastro | null>;
  public user2: Observable<Cadastro | null>;

  constructor(private http: HttpClient)  {
    this.userSubject = new BehaviorSubject<Cadastro | null>(localStorage.getItem( 'user') != null ? JSON.parse( localStorage.getItem('user')!): null);
    this.user2 = this.userSubject.asObservable();
}



      cadastro( nome:string | null, sobrenome: string | null, telefone: string | null, cpf: string | null, login: string | null, senha: string | null) {
        return this.http.post<any>(`/pessoa/cadastro`, { nome, sobrenome,telefone,cpf,login,senha })
            .pipe(map(user2 => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                user2.authdata = window.btoa(nome + ':' + senha  + ':' + telefone  + ':' + cpf  + ':' + login  + ':' +  senha );
                localStorage.setItem('user', JSON.stringify(user2));
                this.userSubject.next(user2);
                return user2;
            }));
    }
  }
