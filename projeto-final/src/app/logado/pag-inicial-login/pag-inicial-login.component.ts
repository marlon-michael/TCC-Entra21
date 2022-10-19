import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'types/types';
import { AuthenticationService } from '../helpers/auth.service';

@Component({
  selector: 'app-pag-inicial-login',
  templateUrl: './pag-inicial-login.component.html',
  styleUrls: ['./pag-inicial-login.component.css']
})

export class PagInicialLoginComponent implements OnInit {
  title = 'ConnActionAngularProject';

  user: User | null = null;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.user.subscribe(x => this.user = x);
    }
  ngOnInit(): void {
    console.log(this.user?.id);
  }

    logout() {
        this.authenticationService.logout();
    }
}
