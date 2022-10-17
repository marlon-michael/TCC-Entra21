import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PagInicialLoginComponent } from './pag-inicial-login/pag-inicial-login.component';
import { LocalizadorComponent } from './localizador/localizador.component';
import { ConfiguracoesComponent } from './configuracoes/configuracoes.component';
import { NavComponent } from './nav/nav.component';
import { AjudaComponent } from './ajuda/ajuda.component';

@NgModule({
  declarations: [
    AppComponent,
    PagInicialLoginComponent,
    LocalizadorComponent,
    ConfiguracoesComponent,
    NavComponent,
    AjudaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
