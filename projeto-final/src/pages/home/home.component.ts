import { Component, OnInit } from '@angular/core';
import { User } from 'types/types';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User | null = null;
  constructor() { }

  ngOnInit(): void {
  }

}
