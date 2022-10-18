// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { ActivatedRoute, Router } from '@angular/router';
// import { first } from 'rxjs/operators';
// import { AuthenticationService } from 'src/app/logado/helpers/auth.service';

// @Component({
//   selector: 'app-cadastro',
//   templateUrl: './cadastro.component.html',
//   styleUrls: ['./cadastro.component.css']
// })
// export class CadastroComponent implements OnInit {
//   loginForm: FormGroup = this.formBuilder.group({
//     username: ['', Validators.required],
//     password: ['', Validators.required]
//   });
//   loading = false;
//   submitted = false;
//   returnUrl: string = this.route.snapshot.queryParams['returnUrl'];;
//   error = '';

//   constructor(
//       private formBuilder: FormBuilder,
//       private route: ActivatedRoute,
//       private router: Router,
//       private authenticationService: AuthenticationService
//   ) { 
//       // redirect to home if already logged in
//       if (this.authenticationService.userValue) { 
//           this.router.navigate(['/dashboard']);
//       }
//   }
//   ngOnInit(): void {
//     throw new Error('Method not implemented.');
//   }

//   get f() { return this.loginForm.controls; }

//   onSubmit() {
//       this.submitted = true;

//       // stop here if form is invalid
//       if (this.loginForm!.invalid) {
//           return;
//       }

//       this.loading = true;
//       this.authenticationService.login(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value)
//           .pipe(first())
//           .subscribe(
//               (              _data: any) => {
//                   this.router.navigate([this.returnUrl ?? '/dashboard']);
//               },
//               (              error: string) => {
//                   this.error = error;
//                   this.loading = false;
//               });
//   }
// }
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/logado/helpers/auth.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: '../cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit{
  [x: string]: any;

cadastroForm;
  submitted = false;
  succes = false;

  nome='CADASTRAR';
  classesText='border-gradient border-gradient-purple';
  classesCadastro='btn-size submit';

  // private http: HttpClient
  constructor(private formBuilder:FormBuilder){
    this.cadastroForm = this.formBuilder.group({
      nome: ['', Validators.required],
      sobrenome: ['', Validators.required],
      telefone: ['', Validators.required],
      cpf: ['', Validators.required],
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  get f() {
    return this.cadastroForm.controls;
  } 

  cadastrar() {
    this.submitted = true;
    if (this.cadastroForm.invalid) {
        return;
    }
    // this.http.post<any>('/pessoa/cadastro', this.cadastroForm.value)
    // this.http.post<any>('/pessoa/cadastro', this.cadastroForm.value)
    this['loading'] = true;
          this['authenticationService'].login(this['loginForm'].get('username')?.value, this['loginForm'].get('password')?.value)
              .pipe(first())
              .subscribe(
                  (              _data: any) => {
                      this['router'].navigate([this['returnUrl'] ?? '/dashboard']);
                  },
                  (              error: string) => {
                      this['error'] = error;
                      this['loading'] = false;
                  });
      }
  }




// function subscribe(arg0: { next: (response: any) => void; error: (error: any) => void; }) {
//   throw new Error('Function not implemented.');
// }
