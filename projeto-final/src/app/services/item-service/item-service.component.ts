import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Item } from 'types/types';

@Component({
  selector: 'app-item-service',
  templateUrl: './item-service.component.html',
  styleUrls: ['./item-service.component.css']
})
export class ItemServiceComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  getAll() {
    return this.http.get<Item[]>(`/item`);
  }

  getByLocalizador(localizador: string) {
    if (localizador.length < 1) {
      return null;
    }
    return this.http.get<Item[]>(`/item/`+localizador);
  }

}
