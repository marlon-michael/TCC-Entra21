import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Empresa, User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit{
  createForm = this.formBuilder.group({
    cnpj: [''],
    razaoSocial: ['']
  })
  contractForm = this.formBuilder.group({
    nome: ['', Validators.required],
    sobrenome: ['', Validators.required],
    cpf: ['', Validators.required],
    telefone: ['', Validators.required],
    supervisor: ['']
  })
  user: User | null = null
  error = ""
  constructor(
    private http: HttpClient,
    private auth: AuthenticationService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.user = this.auth.userValue;    
  }

  onContract(){
    if (!this.contractForm.valid) return
    this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresaRes => {
      let supervisor: any = {cpf: this.contractForm.get("supervisor")?.value}
      if (supervisor.cpf == "") supervisor = null
      let funcionario = {
        nome:  this.contractForm.get("nome")?.value,
        sobrenome: this.contractForm.get("sobrenome")?.value,
        cpf: this.contractForm.get("cpf")?.value,
        telefone: this.contractForm.get("telefone")?.value,
        supervisor: supervisor,
        empresa: {cnpj: empresaRes.cnpj}
      }
      console.log(funcionario)
      this.http.post(`/funcionario/cadastro`, funcionario).subscribe({
        next: res => this.error = "contratado",
        error: error => this.error = "Erro ao contratar funcionario. Verifique os campos e tente novamente"
      })
    })
  }

  onCreate(): void {
    if (this.createForm.get("cnpj")?.value?.length != 14 || this.createForm.get("razaoSocial")?.value?.length! < 3){
      return
    }
    this.http.post(`/empresa`, {
      gerente:{cpf: this.user?.cpf},
      cnpj: this.createForm.get("cnpj")?.value,
      razaoSocial: this.createForm.get("razaoSocial")?.value
    }).subscribe({
      next: () => this.error = "empresa criada",
      error: () => this.error = "Erro ao cadastrar empresa. Verifique se os campos foram preenchidos corretamente e tente novamente"
    })
  }

}
