import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Itens } from 'types/types';

@Injectable({ providedIn: 'root' })
export class ItemRestController {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Itens[]>(`/item`);
    }
}