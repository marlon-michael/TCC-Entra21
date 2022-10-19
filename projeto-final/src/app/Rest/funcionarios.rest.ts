import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Funcionarios } from 'types/types';

@Injectable({ providedIn: 'root' })
export class FuncionarioRestController {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Funcionarios[]>(`/funcionario`);
    }
}