import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Funcionarios, User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnInit {
  form = this.formBuilder.group({
    nome: ['',Validators.required ],
    sobrenome: ['',Validators.required] ,
    cpf: ['',Validators.required ],
    telefone: ['', Validators.required]
  });

  loading = true;
  funcionarios: Funcionarios[] = [];
  submitted = false;
  returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
  error = '';
  succes = false;
  inserting = false;
  user: User | null = null;

  constructor(
    private auth:AuthenticationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,) { }
    
  ngOnInit() {
    this.user = this.auth.userValue;
    this.http.get<any>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresa => {
      this.http.get<any>(`/funcionario/empresa/${empresa.cnpj}`).subscribe(funcionarios => {
        this.funcionarios = funcionarios;
        this.loading = false;
      });
    });
  }
}
