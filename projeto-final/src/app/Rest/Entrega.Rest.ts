import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrega} from 'types/types';
import { BehaviorSubject, map, Observable } from 'rxjs';


@Injectable({ 
  providedIn: 'root' 
})
export class EntregaRestController {
  private userSubject: BehaviorSubject<Entrega | null>;
  public entrega: Observable<Entrega | null>;

    constructor(private http: HttpClient) {
      this.userSubject = new BehaviorSubject<Entrega | null>(localStorage.getItem( 'user') != null ? JSON.parse( localStorage.getItem('user')!): null);
      this.entrega = this.userSubject.asObservable();
     }

    getAll() {
        return this.http.get<Entrega[]>(`/entrega`);
    }

    // addentrega( tipoEntrega: string , entregador: Pessoa["cpf"] ,entregaTrecho: EntregaTrecho["trecho"] ,itens: Itens[]) {
    //   return this.http.post<any>(`/entrega/addEntrega`, {tipoEntrega , entregador, entregaTrecho, itens})
    //           .pipe(map((entrega: Entrega | null) => {
    //             localStorage.setItem('user', JSON.stringify(entrega));
    //             this.userSubject.next(entrega);
    //             return entrega;
  
    //         }));
    //       }

}