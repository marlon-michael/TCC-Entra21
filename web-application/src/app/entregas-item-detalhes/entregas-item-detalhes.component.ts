import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Empresa, Entrega, EntregaSingleLine, Item, User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-entregas-item-detalhes',
  templateUrl: './entregas-item-detalhes.component.html',
  styleUrls: ['./entregas-item-detalhes.component.css']
})
export class EntregasItemDetalhesComponent{
 
  formEdit: any;
  itemForm: FormGroup = this.formBuilder.group({
    localizador:  ['', Validators.required],
    entregador:  [''],
    status: ['']
  });
  error = "";
  user: User | null = null;
  entregas: EntregaSingleLine[] = [];
  itens: {
    id: number
    item: Item
  }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthenticationService,
    private http: HttpClient
  ) {
    this.formEdit = this.formBuilder.group({
      id: ['', Validators.required],
      entregador: [''],
      status: [''],
      localizador: ['']
    });
  }

  ngOnInit(): void {
    this.user = this.auth.userValue;
    if (this.user?.cargo == "FUNCIONARIO"){
      this.http.get<Entrega[]>(`/entrega/entregador/${this.user?.cpf}`).subscribe(entregasResult => {
        this.mapEntrega(entregasResult)
      });
    }
    if (this.user?.cargo == "SUPERVISOR"){
      this.http.get<Empresa>(`/empresa/funcionario/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.mapEntrega(entregasResult)
        });
      });
    }
    if (this.user?.cargo == "GERENTE"){
      this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.mapEntrega(entregasResult)
        });
      });
    }
  }

  onEdit():void{
    if (this.user?.cargo == "GERENTE"){
      this.http.get<Empresa>(`/empresa/gerente/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.edit(entregasResult)
        });
      });
    }
    if (this.user?.cargo == "SUPERVISOR" || this.user?.cargo == "FUNCIONARIO"){
      this.http.get<Empresa>(`/empresa/funcionario/${this.user?.cpf}`).subscribe(empresaRes => {
        this.http.get<Entrega[]>(`/entrega/empresa/${empresaRes?.cnpj}`).subscribe(entregasResult => {
          this.edit(entregasResult)
        });
      });
    }
  }

  edit(entregas: Entrega[]):void{
    var _id = this.formEdit.get("id")?.value;
    var _cpf = this.formEdit.get("entregador")?.value;
    var _status: string = this.formEdit.get("status")?.value;
    var _localizador: string = this.formEdit.get("localizador")?.value
    entregas.forEach(entrega=>{
      if (_id != entrega.idEntrega) return;
      if (_status.trim() != "" && this.user?.cargo == "FUNCIONARIO"){
        if (_localizador.trim() != "") this.http.put(`/item/${_localizador}`, {status: _status.toUpperCase()})
        else entrega.itens.forEach(item => {
          this.http.put(`/item/${item.localizador}`, {status: _status.toUpperCase()})
        })
      }
      if (_cpf.trim() == "" || _cpf.trim().length != 11 || this.user?.cargo != "GERENTE" && this.user?.cargo != 'SUPERVISOR') return
      if (entrega.entregador.cpf = _cpf){
        this.http.put(`/entrega/${_id}`, {entregador:{cpf: _cpf}})
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
      entrega.itens.forEach(item => {
        this.itens.push({
          id: entrega.idEntrega,
          item: item
        })
      })
    });
  }
}
