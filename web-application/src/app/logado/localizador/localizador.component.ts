import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Itens, User } from "types/types";
import { AuthenticationService } from '../helpers/auth.service';


@Component({
  selector: 'app-localizador',
  templateUrl: './localizador.component.html',
  styleUrls: ['./localizador.component.css']
})
export class LocalizadorComponent implements OnInit {
  loading = false;
  submitted = false;
  returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
  error = '';
  succes = false;
  result: any;
  permitido = false;
  itens: Itens[] = [];
  role: any;
  user: User | null = null;
  formLocalizador: FormGroup = this.formBuilder.group({
    localizador: ['', Validators.required]
  });

  constructor(
    private http: HttpClient,
    private auth:AuthenticationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute) {
    }

  ngOnInit() {
    this.user = this.auth.userValue;
    if(this.user != null && (this.user.role == 'PESSOA')){
      this.permitido == false;
    }
  }

  onSearch() {
    this.itens = []
    this.http.get<any>(`/item/${this.formLocalizador.get("localizador")?.value}`).subscribe(result => {
      if (result.localizador == undefined) return;
      if (result.localizador.length > 0){
        this.itens.push(result)
      }
    });
    this.http.get<any>(`/item/pessoa/${this.formLocalizador.get("localizador")?.value}`).subscribe(result => {
      result.forEach((item: Itens) => {
        this.itens.push(item)
      });
    });
  }
}

