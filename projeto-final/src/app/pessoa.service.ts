import { Injectable } from '@angular/core';
import { Funcionarios } from 'types/types';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor() { }
}

// remove = (funcionario: Funcionarios) => {
//   funcionario = funcionario.filter((h: Funcionarios) => h.cpf! != funcionario.cpf!);
// }

// edit = (funcionarioCPF: string) => {
//   this.editingHero = this.funcionario.find((h: Funcionarios) => h.cpf! == funcionarioCPF!);
//   this.inserting = true;

