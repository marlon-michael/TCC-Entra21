import { Component, OnInit } from '@angular/core';
import { Item } from 'types/types';
import { ItemServiceComponent } from '../services/item-service/item-service.component';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  item: Item | null | undefined;

  constructor(private itemService: ItemServiceComponent) { }

  ngOnInit(): void {
  }

  onFindItem(): void {
    // itemService.getByLocalizador();
  }

}
