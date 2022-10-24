import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Entrega, Itens } from 'types/types';
import { EntregaRestController } from '../Rest/Entrega.Rest';

@Component({
  selector: 'app-entregas',
  templateUrl: './entregas.component.html',
  styleUrls: ['./entregas.component.css']
})
export class EntregasComponent implements OnInit {
  
  @Input() item!: Itens;
  @Output() itemDelete = new EventEmitter<Itens>();
  @Output() itemEdit = new EventEmitter<string>();

 entregas: Entrega[] = [];
// itens: Itens[] = [];
// trecho: Trecho[] = [];
 loading = false;
 submitted = false;
 returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
 error = '';
 succes = false;
  constructor(  
    private EntregaRestController: EntregaRestController,
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.loading = true;
    this.EntregaRestController.getAll().pipe(first()).subscribe((funcionarios: any) => {
        this.loading = false;
        this.entregas = funcionarios;
    });
  }

  onDeleteEntrega = () => {
    this.itemDelete.emit(this.item);
  }

  onEditEntrega = () => {
    this.itemEdit.emit(this.item.localizador);
  }

}
