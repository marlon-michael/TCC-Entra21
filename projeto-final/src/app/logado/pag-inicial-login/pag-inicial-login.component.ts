import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';


type User = {
  id: number;
  login: string;
  password: string;
  authdata?: string;
}

@Component({
  selector: 'app-pag-inicial-login',
  templateUrl: './pag-inicial-login.component.html',
  styleUrls: ['./pag-inicial-login.component.css']
})

export class PagInicialLoginComponent implements OnInit {
  
  user: User | null = null;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  logout() {
    this.authenticationService.logout();
  }

  ngOnInit(): void {
  }

}
