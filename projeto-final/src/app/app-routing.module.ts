import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfiguracoesComponent } from './configuracoes/configuracoes.component';
import { LocalizadorComponent } from './localizador/localizador.component';

const routes: Routes = [
 { path: 'localizador', component: LocalizadorComponent},
 { path: 'config', component: ConfiguracoesComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
