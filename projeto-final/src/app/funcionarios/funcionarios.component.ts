import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Funcionarios } from 'types/types';
import { FuncionarioRestController } from '../Rest/funcionarios.rest';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnInit {
  loading = false;
  funcionarios: Funcionarios[] = [];

  constructor(private funcionarioRestController: FuncionarioRestController) { }

  ngOnInit(): void {
    this.loading = true;
    this.funcionarioRestController.getAll().pipe(first()).subscribe(funcionarios => {
        this.loading = false;
        this.funcionarios = funcionarios;
    });
  }

}
