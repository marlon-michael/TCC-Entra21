import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { User } from 'types/types';
import { AuthenticationService } from '../helpers/auth.service';

@Component({
  selector: 'app-configuracoes',
  templateUrl: './configuracoes.component.html',
  styleUrls: ['./configuracoes.component.css']
})
export class ConfiguracoesComponent{
  dataForm = this.formBuilder.group({
    nome: [''],
    sobrenome: [''],
    telefone: [''],
    login: [''],
    senha: ['']
  })
  user: User | null = null
  error = ""
  constructor(
    private http: HttpClient,
    private auth: AuthenticationService,
    private formBuilder: FormBuilder
  ) { }

  onUpdate(): void {
    this.user = this.auth.userValue;
    this.http.put(`/pessoa/${this.user.cpf}`, this.dataForm.value).subscribe(
      res => this.error = "feito!",
      error => this.error = "Não foi possível atualizar as informações. Tente novamente mais tarde"
    )
  }

}
