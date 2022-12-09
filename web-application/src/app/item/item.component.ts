import { HttpClient } from '@angular/common/http';
import { Component} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'types/types';


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})

export class ItemComponent{
  itemForm: FormGroup = this.formBuilder.group({
    nomeRecebedor:  ['', Validators.required],
    localEntrega:  ['', Validators.required],
    pessoaItem: ['']
  });
  itens: Item[] = [];
  error = '';
  constructor(
    private http: HttpClient, 
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) { }

  AddItem() {
    if (this.itemForm.invalid) {
      this.error = "Os campos não foram preenchidos corretamente";
      return;
    }
    this.error = "CPF não encontrado. Este cpf pode não estar cadastrado em nossos bancos";
    this.http.get<any>(`/pessoa/${this.itemForm.get("pessoaItem")?.value}`).subscribe(result => {
      this.error = "";
      let item = this.itemForm.value;
      item['pessoaItem'] = {"cpf": result.cpf}
      this.http.post<any>('/item/additem', item)
      .subscribe({
        next: (response) => {
          // this.router.navigateByUrl('/localizador');
          this.error = "Item adicionado com sucesso - localizador: " + response.localizador;
        },
        error: (error) => this.error = "Os dados informados podem estar incorretos ou não estarem presentes em nossos bancos. Verifique e tente novamente",
      });
    });
  };
}
