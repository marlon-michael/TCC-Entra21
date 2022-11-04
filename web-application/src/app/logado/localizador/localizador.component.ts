import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first, map, Observable, tap } from 'rxjs';

import { ItemRestController } from 'src/app/Rest/itens.rest';
import { Itens, User } from "types/types";
import { AuthenticationService } from '../helpers/auth.service';
// import { Itenspessos } from 'types/types';


@Component({
  selector: 'app-localizador',
  templateUrl: './localizador.component.html',
  styleUrls: ['./localizador.component.css']
})
export class LocalizadorComponent implements OnInit {
itens: Itens[] = [];

role:any;
user: User | null = null;
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
// user: any;
permitido = false;

    
  constructor(private itemRestController: ItemRestController,
    private http: HttpClient,
    private auth:AuthenticationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {
    }




  ngOnInit() {
    this.user = this.auth.userValue;
    if(this.user != null && (this.user.role == 'PESSOA')){
      this.permitido == false;
  }
  }


  
 
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

