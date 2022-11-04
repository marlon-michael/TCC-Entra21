import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-entregas-item-detalhes',
  templateUrl: './entregas-item-detalhes.component.html',
  styleUrls: ['./entregas-item-detalhes.component.css']
})
export class EntregasItemDetalhesComponent implements OnInit {

  itemForm: any;

  constructor(private route: ActivatedRoute, private fb: FormBuilder) { 
    this.route.params.subscribe(params => console.log(params));
    this.itemForm = this.fb.group({});
  }

  ngOnInit(): void {
  }

}
