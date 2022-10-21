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
import { AjudaComponent } from './ajuda/ajuda.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';



import { EntregasComponent } from './entregas/entregas.component';
import { EntregasItemDetalhesComponent } from './entregas-item-detalhes/entregas-item-detalhes.component';
import { ItemComponent } from './item/item.component';



const routes: Routes = [
{path: '', component:HomeComponent},
{path: 'additem', component: ItemComponent},
{path: 'login', component:LoginComponent},
{path: 'about',component:AboutComponent},
{path: 'contact',component:ContactComponent},
{ path: 'localizador', component: LocalizadorComponent},
{ path: 'config', component: ConfiguracoesComponent},
{path: 'cadastro', component: CadastroComponent},
{path: 'ajuda', component: AjudaComponent},
{path: 'funcionarios', component: FuncionariosComponent},
{path: 'edit', component: FuncionariosComponent},
{path: 'entregas', component: EntregasComponent},
{path: 'entregas/:localizador', component: EntregasItemDetalhesComponent},

{path: '**', redirectTo: '/'},
];
// , canActivate: [AuthGuard] se tiver logado

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
