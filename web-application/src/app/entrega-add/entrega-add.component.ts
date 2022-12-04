import { HttpClient } from '@angular/common/http';
import { Component} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-entrega-add',
  templateUrl: './entrega-add.component.html',
  styleUrls: ['./entrega-add.component.css']
})
export class EntregaAddComponent {
  entregaForm: FormGroup = this.formBuilder.group({
    motoristaCPF:  ['', Validators.required],
    tipo:  ['', Validators.required],
    localizadores: ['', Validators.required],
    placaCarro: ['', Validators.required],
    trechos: ['', Validators.required]
  });
  submitted = false;
  error = '';
  constructor(
    private http: HttpClient, 
    private formBuilder: FormBuilder
  ) { }

  addEntrega() {
    this.submitted = false;
    var placaCarro: string = this.entregaForm.get("placaCarro")?.value
    var localizadores: string[] = this.entregaForm.get("localizadores")?.value.split(";")
    var trechos: string[] = this.entregaForm.get("trechos")?.value.split(";")
    if (this.entregaForm.invalid) {
      this.error = "Os campos não foram preenchidos corretamente";
      return;
    }
    var itens: any[] = []
    var entregaTrechos: any[] = []
    for (var i = 1; i < trechos.length-1; i++){
      entregaTrechos.push({
        carro: {placa: placaCarro},
        trecho: {
          localInicio: trechos[i-1].trim(),
          localFim: trechos[i].trim(),
        }
      })
    }
    localizadores.forEach(_localizador => {
      itens.push({
        localizador: _localizador.trim()
      })
    })

    this.error = "Os dados informados podem estar incorretos ou não estarem presentes em nossos bancos";
    this.http.post<any>(`/entrega`, {
      tipoEntrega: this.entregaForm.get("tipo")?.value.toUpperCase(),
      entregador: {cpf: this.entregaForm.get("motoristaCPF")?.value},
      entregaTrecho: entregaTrechos,
      itens: itens
    }).subscribe(res => this.error = "")
  };
}

