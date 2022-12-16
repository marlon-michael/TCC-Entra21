import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/logado/helpers/auth.service';
import { User } from 'types/types';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent{
  title = 'web-application';
  user: User | null = null;

  constructor(private authenticationService: AuthenticationService) {
    this.authenticationService.user.subscribe(user => this.user = user)
  }

  logout() {
    this.authenticationService.logout();
  }
}

