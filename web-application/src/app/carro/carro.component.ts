import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Empresa, User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-carro',
  templateUrl: './carro.component.html',
  styleUrls: ['./carro.component.css']
})

export class CarroComponent implements OnInit {
  carroForm = this.formBuilder.group({
    placa: ['', Validators.required],
    tipo: ['', Validators.required]
  })
  user: User | null = null
  carros: any = null
  page_state = "SELECT"
  
  error = ""
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private auth: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.user = this.auth.userValue
    this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe({
      next: empresaRes => {
        this.http.get(`/carro/empresa/${empresaRes.cnpj}`).subscribe({
          next: (carro) => this.carros = carro,
          error: () => this.error = "Não foi possível adicionar este veículo a sua frota"
        })
      }
    })
  }

  addCarro():void {
    if (!this.carroForm.valid){
      this.error = "Os campos não foram preenchidos corretamente"
      return
    }
    this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe({
      next: empresaRes => {
        let carro = {
          placa: this.carroForm.get("placa")?.value,
          tipoCarro: this.carroForm.get("placa")?.value,
          empresa:{cnpj: empresaRes.cnpj}
        }
        this.http.post(`/carro`, carro).subscribe({
          next: () => this.error = "Concluido",
          error: () => this.error = "Não foi possível adicionar este veículo a sua frota"
        })
      }
    })
  }

}
