import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemRestController } from '../Rest/itens.rest';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  LocalForm: FormGroup = this.formBuilder.group({
    search: ['']
  });

  constructor(
    private itemRestController: ItemRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.http.get<any>(`/item/${this.LocalForm.get("localizador")?.value}`).subscribe(result => {
      this.LocalForm.patchValue = result
      console.log(result)
    });
  }

  search(): void {
    this.http.get<any>(`/item/${this.LocalForm.get("localizador")?.value}`).subscribe(result => {
      this.LocalForm.patchValue = result
      console.log(result)
    });
  }
    

}
