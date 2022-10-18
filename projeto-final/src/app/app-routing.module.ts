import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule }from '@angular/common/http';
import { AboutComponent } from 'src/pages/about/about.component';
import { ContactComponent } from 'src/pages/contact/contact.component';
import { HomeComponent } from 'src/pages/home/home.component';
import { LoginComponent } from 'src/pages/login/login.component';
import { ConfiguracoesComponent } from 'src/app/logado/configuracoes/configuracoes.component';
import { LocalizadorComponent } from 'src/app/logado/localizador/localizador.component';
import { PagInicialLoginComponent } from 'src/app/logado/pag-inicial-login/pag-inicial-login.component'; 

const routes: Routes = [
{path: '', component:HomeComponent},
{path:'login',component:LoginComponent},
{path: 'about',component:AboutComponent},
{path: 'contact',component:ContactComponent},
{path: 'local', component:LocalizadorComponent},
{path: 'config', component:ConfiguracoesComponent},
{path: 'app-pag-inicial-login', component:PagInicialLoginComponent}
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
