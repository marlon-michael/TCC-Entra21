import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/logado/helpers/auth.service';
import { User } from 'types/types';
// import { AuthenticationService } from 'src/app/helpers/auth.service';
// import { User } from 'types/types';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  title = 'web-application';

  user: User | null = null;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.user.subscribe(x => this.user = x);
    }
  ngOnInit(): void {
    console.log(this.user?.idPessoa);
  }

  logout() {
    this.authenticationService.logout();
    
}

}

