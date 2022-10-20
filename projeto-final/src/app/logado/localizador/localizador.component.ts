import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';

import { ItemRestController } from 'src/app/Rest/itens.rest';
import { Itens } from "/types/types';
// import { Itenspessos } from 'types/types';

@Component({
  selector: 'app-localizador',
  templateUrl: './localizador.component.html',
  styleUrls: ['./localizador.component.css']
})
export class LocalizadorComponent implements OnInit {

  itens: Itens[] = [];
  // itenspessos: ItensPessos[] = [];

  constructor(private itemRestController: ItemRestController) { }

  ngOnInit(): void {
    this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
      this.itens = itens;
  });
    // this.itemRestController.getAll().pipe(first()).subscribe((itenspessos) =>{
    //   this.itenspessos = itenspessos;
    // });
  
  }

}
