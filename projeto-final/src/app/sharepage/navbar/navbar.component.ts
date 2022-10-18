import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { AuthenticationService } from 'src/app/helpers/auth.service';
// import { User } from 'types/types';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
//   user: User | null = null;
//   constructor( private router: Router,
//     private authenticationService: AuthenticationService
// ) {
//     this.authenticationService.user.subscribe(x => this.user = x);
// }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

// logout() {
//     this.authenticationService.logout();
// }
}




