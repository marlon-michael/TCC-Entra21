import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from 'types/types';
import { ItemRestController } from '../Rest/itens.rest';
import { ItemServiceComponent } from '../services/item-service/item-service.component';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  item: Item[] = [];
  itemForm: FormGroup = this.formBuilder.group({
    localizador: ['', Validators.required],
    status:  ['', Validators.required],
    nomeRecebedor:  ['', Validators.required],
    localEntrega:  ['', Validators.required],
    pessoaItem: ['', Validators.required]
  });
  loading = false;
submitted = false;
returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
error = '';
succes = false;

  constructor(private itemService: ItemServiceComponent,
    private itemRestController: ItemRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
  }

  onFindItem(): void {
    // itemService.getByLocalizador();
  }

  AddItem() {
    this.submitted = true;
    if (this.itemForm.invalid) {
        return;
    }
    console.log(this.itemForm.value);
    this.http.post<any>('/item/additem', this.itemForm.value)
      .subscribe({
        next: (response) => {
          console.log(response);
          this.router.navigateByUrl('/localizador');
        },
        error: (error) => console.log(error),
      });
  }
}
