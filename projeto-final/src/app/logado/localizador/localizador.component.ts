import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type Item = {
  localizador?: String,
  status: String,
  localEntrega: String,
  nomeRecebedor: String
}

@Component({
  selector: 'app-localizador',
  templateUrl: './localizador.component.html',
  styleUrls: ['./localizador.component.css']
})
export class LocalizadorComponent implements OnInit {
  allItens:Item[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Item[]>("/item").subscribe(item=>this.allItens=item);
  }

}
