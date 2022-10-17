import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pag-inicial-login',
  templateUrl: './pag-inicial-login.component.html',
  styleUrls: ['./pag-inicial-login.component.css']
})
export class PagInicialLoginComponent implements OnInit {
logout() {
throw new Error('Method not implemented.');
}
user: any;

  constructor() { }

  ngOnInit(): void {
  }

}
