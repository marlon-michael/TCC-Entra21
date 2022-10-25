import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
// results$: Observable<any> | undefined;
// @Input() informacao: any;

// @Output()
itens: Itens[] = [];

// total:number | undefined;
  
//   itens2: ItensPessoas[] = [];
formLocalizador: FormGroup = this.formBuilder.group({

  local: ['', Validators.required],
  status:  ['', Validators.required],
  nomeRecebedor:  ['', Validators.required],
  localEntrega:  ['', Validators.required],
  pessoaItem: ['', Validators.required],
  funcionario: ['', Validators.required]
});
loading = false;
submitted = false;
returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
error = '';
succes = false;
  result: any;


    
  constructor(private itemRestController: ItemRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }




  ngOnInit() {

//     this.http.get<any>(`/pessoa/${this.formLocalizador.get("pessoaItem")?.value}`).subscribe(result => {
//       let item = this.formLocalizador.value;
//       item['pessoaItem'] = {"cpf": result.cpf}  
//       this.itemRestController.getbycpf().subscribe(item => {
//   this.item = item;
// });
 // });

    // this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
    //   this.item = itens;

    // });


    //   this.http.get<Itens[]>(`/item/pessoa/${this.formLocalizador.get("pessoaItem")?.value}`, item)
    //   .subscribe({
    //     next: (response) => {
    //       console.log(response);
    //     },
    //     error: (error) => console.log(error),
    //   });
    // });

  };



//
    // this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
    //   this.item = itens;
    // });
  
 
  onSearch() {
    this.http.get<any>(`/item/pessoa/${this.formLocalizador.get("local")?.value}`).subscribe(result => {
      if (result.length > 1){
        this.itens = result
      }else{
        this.itens = [result]
      }
      console.log(result);
    });
  }
}
