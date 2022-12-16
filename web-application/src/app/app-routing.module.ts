import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AboutComponent } from 'src/pages/about/about.component';
import { ContactComponent } from 'src/pages/contact/contact.component';
import { HomeComponent } from 'src/pages/home/home.component';
import { ConfiguracoesComponent } from 'src/app/logado/configuracoes/configuracoes.component';
import { LocalizadorComponent } from './logado/localizador/localizador.component'; 
import { LoginComponent } from 'src/pages/login/login.component';
import { CadastroComponent } from 'src/pages/login/cadastro/cadastro.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';
import { EntregasItemDetalhesComponent } from './entregas-item-detalhes/entregas-item-detalhes.component';
import { ItemComponent } from './item/item.component';
import { EntregaAddComponent } from './entrega-add/entrega-add.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { CarroComponent } from './carro/carro.component';


const routes: Routes = [
  {path: '', component:HomeComponent},
  {path: 'additem', component: ItemComponent},
  {path: 'login', component:LoginComponent},
  {path: 'about',component:AboutComponent},
  {path: 'contact',component:ContactComponent},
  {path: 'localizador', component: LocalizadorComponent},
  {path: 'config', component: ConfiguracoesComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'funcionarios', component: FuncionariosComponent},
  {path: 'edit', component: FuncionariosComponent},
  {path: 'entregas', component: EntregasItemDetalhesComponent},
  {path: 'addentrega', component: EntregaAddComponent},
  {path: 'empresa', component: EmpresaComponent},
  {path: 'carro', component: CarroComponent},
  {path: '**', redirectTo: '/'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
