import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'types/types';
import { AuthenticationService } from './logado/helpers/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  user: User | null = null;
  constructor(
      private router: Router,
      private authenticationService: AuthenticationService
  ) {
      this.authenticationService.user.subscribe(x => this.user = x);
  }
  ngOnInit(): void {}
  logout() {
      this.authenticationService.logout();
  }
}