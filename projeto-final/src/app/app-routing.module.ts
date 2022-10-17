import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AjudaComponent } from './ajuda/ajuda.component';
import { ConfiguracoesComponent } from './configuracoes/configuracoes.component';
import { LocalizadorComponent } from './localizador/localizador.component';
import { PagInicialLoginComponent } from './pag-inicial-login/pag-inicial-login.component';

const routes: Routes = [
 { path: 'localizador', component: LocalizadorComponent},
 { path: 'config', component: ConfiguracoesComponent},
 {path: 'inicio', component: PagInicialLoginComponent},
 {path: 'ajuda', component: AjudaComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
