import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Entrega} from 'types/types';
import { BehaviorSubject, Observable } from 'rxjs';


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

}