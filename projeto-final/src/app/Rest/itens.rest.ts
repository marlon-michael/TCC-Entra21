import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {  Itens, Pessoa} from 'types/types';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { AuthenticationService } from '../logado/helpers/auth.service';


@Injectable({ 
  providedIn: 'root' 
})
export class ItemRestController {
  private userSubject: BehaviorSubject<Itens | null>;
  public localizador: Observable<Itens | null>;

  UserId: any;
    constructor(private http: HttpClient,
      private auth: AuthenticationService) {
      this.userSubject = new BehaviorSubject<Itens | null>(localStorage.getItem( 'user') != null ? JSON.parse( localStorage.getItem('user')!): null);
      this.localizador = this.userSubject.asObservable();
     }

    
    getAll() {
   
      return this.http.get<Itens[]>(`/item`);
  };

    getbycpf() {
          return this.http.get<Itens[]>(`/item//pessoa/{cpf}`);
    }

    getLocalizador(){
      return this.http.get<Itens[]>(`/item/localizador`);
    }

    // getBycpf = () => {
    //   this.UserId = this.auth.userValue.id;
    //   let queryParams = new HttpParams();
    //   queryParams = queryParams.append("IdUsuario", this.UserId);
    //   return this.http.get<Itens[]>('/item/pessoa', {params: queryParams});
    // }

    // getbycpf() {
    //      this.http.get<any>(`/item/${this.formLocalizador.get("pessoaItem"`)?.value}`).subscribe(result => {
    //       let item = this.formLocalizador.value;
    //       item['pessoaItem'] = {"cpf": result.cpf}
    //     return this.http.get<ItensPessoas[]>(`/item/pessoa/{cpf}`);
        
    // }


//localizador: string | null,status: string | null, , funcionario: Funcionario["empresa"]

//ADICIONAR UM ITEM
localiza( localizador: string | null,status: string | null,nomeRecebedor: string | null,localEntrega: string | null, pessoaItem: Pessoa["cpf"]) {

    return this.http.post<any>(`/item/additem`, { localizador,status, nomeRecebedor, localEntrega, pessoaItem })
            .pipe(map((localizador: Itens | null) => {
              localStorage.setItem('user', JSON.stringify(localizador));
              this.userSubject.next(localizador);
              return localizador;

          }));
        }

    // this.http.post(`${ this.apiURL }/produtos`, produto)
    //         .subscribe(
    //           resultado => {
    //             console.log(resultado)
    //           },
    //           erro => {
    //             if(erro.status == 400) {
    //               console.log(erro);
    //             }
    //           }
    //         );

      }

function subscribe(arg0: (resultado: any) => void, arg1: (erro: { status: number; }) => void) {
    throw new Error('Function not implemented.');
}
    
