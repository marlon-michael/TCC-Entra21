import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'types/types';
import { AuthenticationService } from '../logado/helpers/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
    title = 'PROJETO-FINAL';
  
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
  