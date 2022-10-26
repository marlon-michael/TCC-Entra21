import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import {  Funcionarios } from 'types/types';
import { FuncionarioRestController } from '../Rest/funcionarios.rest';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnInit {

    form = this.formBuilder.group({
      nome: ['',Validators.required ],
      sobrenome: ['',Validators.required] ,
      cpf: ['',Validators.required ],
      telefone: ['', Validators.required]
    });

  loading = true;
  funcionarios: Funcionarios[] = [];
submitted = false;
returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
error = '';
succes = false;
inserting = false;

// funcionariobuscar: Funcionario[] = [];


  constructor(private funcionarioRestController: FuncionarioRestController, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,) { }
  // ngOnInit(): void {
  //   throw new Error('Method not implemented.');
  // }
  ngOnInit() {
   
    this.funcionarioRestController.getAll().pipe(first()).subscribe((funcionarios: any) => {
      this.loading = false;
      console.log(funcionarios);
        this.funcionarios = funcionarios;
    });

     // this.itemRestController.getAll().pipe(first()).subscribe((itens: Itens[]) => {
    //   this.item = itens;

    // });
    // this.http.get<any>(`/empresa/${this.form.get("empresa")?.value}`).subscribe(result => {
    //   let funcio = this.form.value;
    //   this.loading = false;
    //   funcio['empresa'] = {"cnpj": result.cnpj}
    // this.funcionarioRestController.getbycnpj().pipe(first()).subscribe((funcionariobuscar: Funcionario[]) => { 
    //   console.log(this.funcionariobuscar.values);
    // });
    
  
  
     };
     
    //  AddItem() {
    //   this.submitted = true;
    //   if (this.form.invalid) {
    //       return;
    //   }
    //   console.log(this.form.value);
  

    //   this.http.get<any>(`/empresa/${this.form.get("empresa")?.value}`).subscribe(result => {
    //     let item = this.form.value;
    //     item['empresa'] = {"cnpj": result.cnpj}
    //     this.http.post<any>('funcionario/addfuncionario', item)
    //     .subscribe({
    //       next: (response) => {
    //         console.log(response);
    //         this.router.navigateByUrl('/funcionarios');
    //       },
    //       error: (error) => console.log(error),
    //     });
    //   });
  
    }

  // onSearch(){

  //   this.http.get<any>(`/funcionario/empresa/${this.form.get("search")?.value}`).subscribe(result => {
  //     if (result.length > 1){
  //       this.funcionariobuscar = result
  //     }else{
  //       this.funcionariobuscar = [result]
  //     }
  //     console.log(result);
  //   });  

    // this.funcionarioRestController.getbycnpj().pipe(first()).subscribe((funcionariobuscar: Funcionario[]) => { 
    //   console.log(this.funcionariobuscar.values);
    // });
    // },
    // error: (console.error();
    // ) => console.log(Error),
  
// };





// // editingFuncionario: Funcionario | null | undefined = null;
// inserirFuncionario = () => {
//     this.funcionarioEdit;
//     this.inserting = true;
//   } 

//   // save = (funcionario: Funcionarios) => {
//   //   if (funcionario.cpf == null) {
//   //     funcionario.cpf = (
//   //       this.funcionario.length > 0 ? 
//   //       this.funcionario.map((h: Funcionarios) => h.cpf!)
//   //       .sort()[this.funcionario.length-1] : 0)+1
//   //     this.funcionario.push(funcionario)
//   //   } else {
//   //     let pos = this.funcionario.findIndex((h: Funcionarios) => h.cpf! == funcionario.cpf!)
//   //     this.funcionario[pos] = funcionario;
//   //   }
//   // }



  
