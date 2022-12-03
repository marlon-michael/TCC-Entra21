import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Empresa, Entrega, EntregaSingleLine, User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-entregas-item-detalhes',
  templateUrl: './entregas-item-detalhes.component.html',
  styleUrls: ['./entregas-item-detalhes.component.css']
})
export class EntregasItemDetalhesComponent{
 
  itemForm: FormGroup = this.formBuilder.group({
    localizador:  ['', Validators.required],
    entregador:  [''],
    status: ['']
  });
  error = "";
  formEdit: any;
  user: User | null = null;
  entregas: EntregaSingleLine[] | null = [];

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthenticationService,
    private http: HttpClient
  ) {
    this.formEdit = this.formBuilder.group({
      id: ['', Validators.required],
      entregador: [''],
      status: ['']
    });
  }

  ngOnInit(): void {
    this.user = this.auth.userValue;
    if (this.user?.role == "FUNCIONARIO"){
      this.http.get<Entrega[]>(`/entrega/entregador/${this.user?.cpf}`).subscribe(entregasResult => {
        this.mapEntrega(entregasResult)
      });
    }
    if (this.user?.role == "SUPERVISOR"){
      this.http.get<Empresa>(`/empresa/funcionario/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.mapEntrega(entregasResult)
        });
      });
    }
    if (this.user?.role == "GERENTE"){
      this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.mapEntrega(entregasResult)
        });
      });
    }
  }

  onEdit():void{
    if (this.user?.role == "GERENTE"){
      this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.edit(entregasResult)
        });
      });
    }
    if (this.user?.role == "SUPERVISOR"){
      this.http.get<Empresa>(`/empresa/funcionario/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.edit(entregasResult)
        });
      });
    }
  }

  edit(entregas: Entrega[]):void{
    var id = this.formEdit.get("id")?.value;
    var cpf = this.formEdit.get("cpf")?.value
    var status = this.formEdit.get("status")?.value;

    entregas.forEach(entrega=>{
      if (id == entrega.idEntrega){
        if (cpf != ""){
          this.http.put(`/entrega/${id}`, {
            entregador:{
              cpf: cpf
            }
          }).subscribe(res => console.log(res))
        }
        if (status != ""){
          entrega.itens.forEach(item => {
            this.http.put(`/item/${item.localizador}`, {
              status: status
            })
          })
        }
      }
    })
  }

  mapEntrega(entregas: Entrega[]): void {
    entregas.forEach(entrega => {
      entrega.entregaTrecho.forEach(trecho => {
        this.entregas?.push({
          id: entrega.idEntrega,
          entregador: entrega.entregador,
          tipoEntrega: entrega.tipoEntrega,
          entregaTrecho: trecho
        })
      })
    });
  }
}
