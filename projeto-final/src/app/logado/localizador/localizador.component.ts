import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { first, map, Observable, tap } from 'rxjs';

import { ItemRestController } from 'src/app/Rest/itens.rest';
import { Itens } from "types/types";
// import { Itenspessos } from 'types/types';


@Component({
  selector: 'app-localizador',
  templateUrl: './localizador.component.html',
  styleUrls: ['./localizador.component.css']
})
export class LocalizadorComponent implements OnInit {
queryField = new FormControl();
readonly SEARCH_URL = 'https://api.cdnjs.com/libraries';
results$: Observable<any> | undefined;

total:number | undefined;
  itens: Itens[] = [];
  // itenspessos: itenspessos[] = [];

  constructor(private itemRestController: ItemRestController,
    private http: HttpClient) { }

  ngOnInit() {
    this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
      this.itens = itens;
  });
  }
 
  onSearch() {
    console.log(this.queryField.value);
    this.results$ = this.http.get(this.SEARCH_URL + '?fields=localizador,nomeRecebedor,localEntrega,status&search=angular').pipe
    (tap((res: any )=> this.total = res.total),
    map((res: any ) => res.itens));
  }
    // this.itemRestController.getAll().pipe(first()).subscribe((itenspessos) =>{
    //   this.itenspessos = itenspessos;
    // });
  
}
