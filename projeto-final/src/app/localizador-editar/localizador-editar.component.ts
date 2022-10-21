import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-localizador-editar',
  templateUrl: './localizador-editar.component.html',
  styleUrls: ['./localizador-editar.component.css']
})
export class LocalizadorEditarComponent implements OnInit {

  itemForm: any;

  constructor(private route: ActivatedRoute, private fb: FormBuilder) { 
    this.route.params.subscribe(params => console.log(params));
    this.itemForm = this.fb.group({});
  }

  ngOnInit(): void {
  }

}
