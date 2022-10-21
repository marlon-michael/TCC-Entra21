import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Itens } from 'types/types';

@Component({
  selector: 'app-entregas',
  templateUrl: './entregas.component.html',
  styleUrls: ['./entregas.component.css']
})
export class EntregasComponent implements OnInit {
  
  @Input() item!: Itens;
  @Output() itemDelete = new EventEmitter<Itens>();
  @Output() itemEdit = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  onDeleteEntrega = () => {
    this.itemDelete.emit(this.item);
  }

  onEditEntrega = () => {
    this.itemEdit.emit(this.item.localizador);
  }

}
