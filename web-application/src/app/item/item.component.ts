import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import {  Itens } from 'types/types';
import { ItemRestController } from '../Rest/itens.rest';

// import { ItemServiceComponent } from '../services/item-service/item-service.component';


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {



  itemForm: FormGroup = this.formBuilder.group({
    localizador: [],
    status:  [],
    nomeRecebedor:  ['', Validators.required],
    localEntrega:  ['', Validators.required],
    pessoaItem: ['', Validators.required],
    // funcionario: ['', Validators.required]
  });
  loading = false;
submitted = false;
returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
error = '';
succes = false;
itens: Itens[] = [];
  constructor(

    private itemRestController: ItemRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.loading = true;
    this.itemRestController.getAll().pipe(first()).subscribe((itens: any) => {
        this.loading = false;
        this.itens = itens;
    });
  }

  AddItem() {
    this.submitted = true;
    if (this.itemForm.invalid) {
        return;
    }
    console.log(this.itemForm.value);
    this.http.get<any>(`/pessoa/${this.itemForm.get("pessoaItem")?.value}`).subscribe(result => {
      let item = this.itemForm.value;
      item['pessoaItem'] = {"cpf": result.cpf}
      this.http.post<any>('/item/additem', item)
      .subscribe({
        next: (response) => {
          console.log(response);
          this.router.navigateByUrl('/itens');
        },
        error: (error) => console.log(error),
      });
    });

  };
  }