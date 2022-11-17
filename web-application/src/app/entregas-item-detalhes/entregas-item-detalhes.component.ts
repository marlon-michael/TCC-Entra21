import { HttpClient } from '@angular/common/http';
import { Component} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


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

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient, 
  ) { }

  // TODO: RETORNAR ENTREGAS COLOCALA NUMA TABELA VERTICAL COM DADOS UTEIS
  getEntrega() {
    console.log(this.itemForm.value)
    if (this.itemForm.invalid || this.itemForm.value.status == "" && this.itemForm.value.entregador == ""){
      this.error = "Os campos não foram preenchidos corretamente";
      return;
    }
    let item = this.itemForm.value;
    this.error = "CPF não encontrado. Este cpf pode não estar cadastrado em nossos bancos";

    this.http.get<any>(`/entregaA/${this.itemForm.value.localizador}`, item)
      .subscribe({
        next: (response) => {
          this.error = "Atualizado"
        },
        error: (error) => console.log(error),
      });
  }

  // TODO: ATT ITEM/ENTREGA SELECIONADA COM RESPECTIVOS DADOS
  attEntrega() {
    console.log(this.itemForm.value)
    if (this.itemForm.invalid || this.itemForm.value.status == "" && this.itemForm.value.entregador == ""){
      this.error = "Os campos não foram preenchidos corretamente";
      return;
    }
    let item = this.itemForm.value;
    this.error = "CPF não encontrado. Este cpf pode não estar cadastrado em nossos bancos";


    this.http.put<any>(`/item/${this.itemForm.value.localizador}`, item)
      .subscribe({
        next: (response) => {
          this.error = "Atualizado"
        },
        error: (error) => console.log(error),
      });
  };

}
