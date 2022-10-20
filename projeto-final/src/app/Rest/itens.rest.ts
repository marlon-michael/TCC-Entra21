import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Itens, ItensPessoas} from 'types/types';
import { map, pipe } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ItemRestController {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Itens[]>(`/item`);
    }
    getbycpf() {
        return this.http.get<ItensPessoas[]>(`/item/pessoa/{cpf}`);
    }

    // getAll() {
    //     return this.http.get<ItensPessos>(`/item"/pessoa/{cpf}"`)
    // }
//    localizador( localizador: string,status: string,nomeRecebedor: string,localEntrega: string) {
//         return this.http.post<any>(`/item/additem`, { localizador,status, nomeRecebedor, localEntrega })
//             .pipe(map(item => {
//                 // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
//                 item.authdata = window.btoa(localizador + ':' + status + ':' + nomeRecebedor+ ':' +localEntrega);
//                 localStorage.setItem('item', JSON.stringify(item));
//                 this.userSubject.next(item);
//                 return item;
//             }));
localizador( localizador: string,status: string,nomeRecebedor: string,localEntrega: string) {
       this.http.post<any>(`/item/additem`, { localizador,status, nomeRecebedor, localEntrega })
            .subscribe(
                (resultado: any) => {
                console.log(resultado)
              },
                (erro: { status: number; }) => {
                if(erro.status == 400) {
                  console.log(erro);
                }
              }
            );
    
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
// function subscribe(arg0: (resultado: any) => void, arg1: (erro: { status: number; }) => void) {
//         throw new Error('Function not implemented.');
//     }
