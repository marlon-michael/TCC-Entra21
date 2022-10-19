
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthenticationService } from 'src/app/logado/helpers/auth.service';
import { cadastroService } from 'src/app/Rest/cadastro.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {

cadastroForm = this.formBuilder.group({
  nome: ['', Validators.required],
  sobrenome: ['', Validators.required],
  telefone: ['', Validators.required],
  cpf: ['', Validators.required],
  login: ['', Validators.required],
  senha: ['', Validators.required]
  // cliente: ['', Validators.required],
  // funcionario: ['', Validators.required],
  // empresa: ['', Validators.required],
});
loading = false;
submitted = false;
returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
error = '';
succes = false;



constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private cadastroService: cadastroService
){}
get f() {
  return this.cadastroForm.controls;
} 

cadastro() {
  this.submitted = true;
  if (this.cadastroForm.invalid) {
      return;
  }
  this.http.post<any>('/pessoa/cadastro', this.cadastroForm.value)
  this.cadastroService.cadastro(this.cadastroForm.get('nome')?.value, this.cadastroForm.get('sobrenome')?.value,this.cadastroForm.get('telefone')?.value,
  this.cadastroForm.get('cpf')?.value,this.cadastroForm.get('login')?.value,this.cadastroForm.get('senha')?.value,)
  .pipe(first())
  .subscribe(
      data => {
          this.router.navigate([this.returnUrl ?? 'home']);
      },
      error => {
          this.error = error;
          this.loading = false;
      });
}}