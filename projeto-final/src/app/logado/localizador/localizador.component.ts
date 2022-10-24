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
item: Itens[] = [];
// total:number | undefined;
  
//   itens2: ItensPessoas[] = [];
formLocalizador: FormGroup = this.formBuilder.group({
  localizador: ['', Validators.required],
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


  // itenspessos: ItensPessos[] = [];

  // itenspessos: itenspessos[] = [];


    
  constructor(private itemRestController: ItemRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }


    


searchText: string = '';
onSearchTextEntered(searchValue: string){
this.searchText = searchValue;
//console.log(this.searchText);
}

  ngOnInit() {
// this.itemRestController.getBycpf().subscribe(item => {
//   this.item = item;
// });

  //   this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
  //     this.item = itens;
  // });

 
  // };
  };

 
  onSearch() {
    this.http.get<any>(`/pessoa/${this.formLocalizador.get("pessoaItem")?.value}`).subscribe(result => {
      let item = this.formLocalizador.value;
      item['pessoaItem'] = {"cpf": result.cpf}
   
  this.itemRestController.getBycpf().subscribe(item => {
    this.item = item;
  });
  });
  };
    // console.log(this.queryField.value);
    // this.results$ = this.http.get(this.SEARCH_URL + '?fields=localizador,nomeRecebedor,localEntrega,status&search=angular').pipe
    // (tap((res: any )=> this.total = res.total),
    // map((res: any ) => res.itens));
    // this.itemRestController.getbycpf().pipe(first()).subscribe((itens2: ItensPessoas[]) => { 
    //   console.log(this.itens2.values);
    //    }); 


        // this.submitted = true;
        // if (this.formLocalizador.invalid) {
        //     return;
        // }
        // console.log(this.formLocalizador.value);
    
      
        // this.http.get<any>(`/item/pessoa${this.formLocalizador.get("pessoaItem")?.value}`).subscribe(result => {
        //   let item = this.formLocalizador.value;
        //   item['pessoaItem'] = {"cpf": result.cpf}

        //   this.http.post<any>('/item/additem', item)
        //   .subscribe({
        //     next: (response) => {
        //       console.log(response);
        //       this.router.navigateByUrl('/itens');
        //     },
        //     error: (error) => console.log(error),
        //   });
        // });
     // }
    }

  
























    // },
    // error: (console.error();
    // ) => console.log(Error),
 
  //     this.itens = itens;
  //     console.log(itens)
  // });
 
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
    // this.http.post<any>('/item/additem', this.Form.value)
    // .subscribe({
    //   next: (response: any) => {
    //     console.log(response);
    //     this.router.navigateByUrl('/login');
    //   },
    //   error: (error) => console.log(error),
    // });




