import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  entrega2: any[][] = [];
 entregas: Entrega[] = [];
 
 entregaForm: FormGroup = this.formBuilder.group({
  tipoEntrega:  ['', Validators.required],
  entregador:  ['', Validators.required],
  entregaTrecho:  ['', Validators.required],
  itens: ['', Validators.required]
   
 });
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
    this.EntregaRestController.getAll().pipe(first()).subscribe((entrega: any) => {
      this.loading = false;
      this.entregas = entrega;
      this.entregas.forEach((entrega)=>{
        entrega.entregaTrecho.forEach((Etrecho)=>{
          let entregaResolved = []
          entregaResolved.push(entrega.entregador.nome);
          entregaResolved.push(entrega.entregador.cpf);
          entregaResolved.push(entrega.tipoEntrega);
          if (Etrecho.Completo){
            entregaResolved.push("Entregue");
          }else{
            entregaResolved.push("Pendente");
          }
          entregaResolved.push(Etrecho.trecho.localInicio);
          entregaResolved.push(Etrecho.trecho.localFim);
          this.entrega2.push(entregaResolved)
        })
      });
      console.log(this.entregas);
      console.log(this.entrega2);
    });
  }

  onSearch() {
    this.http.get<any>(`/entrega/entregador/${this.entregaForm.get("local")?.value}`).subscribe(result => {
      if (result.length > 1){
        this.entregas = result
      }else{
        this.entregas = [result]
      }
      console.log(result);
    });
  }

  AddItem() {
    // this.submitted = true;
    // if (this.entregaForm.invalid) {
    //     return;
    // }
    // console.log(this.entregaForm.value);
  // this.http.get<any>(`/${this.itemForm.get("funcionario")?.value}`)
      //.subscribe(result => {
      //   let item = this.itemForm.value;
      //   item['funcionario'] = {"cpf": result.cpf}
    this.http.get<any>(`/pessoa/${this.entregaForm.get("pessoaItem")?.value}`).subscribe(result => {
      let item = this.entregaForm.value;
      item['pessoaItem'] = {"cpf": result.cpf}
      this.http.post<any>('/item/addEntrega', item)
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
