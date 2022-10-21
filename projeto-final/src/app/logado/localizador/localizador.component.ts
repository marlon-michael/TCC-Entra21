import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { first, map, Observable, tap } from 'rxjs';

import { ItemRestController } from 'src/app/Rest/itens.rest';
import { Itens, ItensPessoas } from "types/types";
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
  itens2: ItensPessoas[] = [];
  // itenspessos: ItensPessos[] = [];

  // itenspessos: itenspessos[] = [];


  constructor(private itemRestController: ItemRestController,
    private http: HttpClient) { }

  ngOnInit() {
    this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
      this.itens = itens;
  });
  }
 
  onSearch() {
    // console.log(this.queryField.value);
    // this.results$ = this.http.get(this.SEARCH_URL + '?fields=localizador,nomeRecebedor,localEntrega,status&search=angular').pipe
    // (tap((res: any )=> this.total = res.total),
    // map((res: any ) => res.itens));
    this.itemRestController.getbycpf().pipe(first()).subscribe((itens2: ItensPessoas[]) => { 
      console.log(this.itens2.values);
    // },
    // error: (console.error();
    // ) => console.log(Error),
  });
  //     this.itens = itens;
  //     console.log(itens)
  // });
  }
    // this.itemRestController.getAll().pipe(first()).subscribe((itenspessos) =>{
    //   this.itenspessos = itenspessos;
    // });
    // localizador(this.itens.get()?.value, this.loginForm.get('password')?.value)
    // .pipe(first())
    // .subscribe(
    //     data => {
    //         this.router.navigate([this.returnUrl ?? 'home']);
    //     },
    //     error => {
    //         this.error = error;
    //         this.loading = false;
    //     });
} 

