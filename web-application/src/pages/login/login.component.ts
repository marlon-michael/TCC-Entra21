import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/logado/helpers/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  loading = false;
  submitted = false;
  returnUrl: string = this.route.snapshot.queryParams['returnUrl'];
  error = '';
  responsedata: any;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService
  ) {
      if (this.authenticationService.userValue) { 
        this.router.navigate(['/home']);
      }
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.valid) {
      this.loading = true;
      this.authenticationService.login(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value).pipe(first()).subscribe({
        next: data => {
          if(data != null){
            this.responsedata = data;
            this.router.navigate([this.returnUrl ?? 'home']);
            // this.router.navigateByUrl('/');
          }
        },
        error: () => this.error = "Falha ao realizar o login, Verifique os campos e tente novamente"
      });
      this.loading = false;
    }
  }

}