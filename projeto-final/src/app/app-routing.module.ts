import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule }from '@angular/common/http';
import { AboutComponent } from 'src/pages/about/about.component';
import { ContactComponent } from 'src/pages/contact/contact.component';
import { HomeComponent } from 'src/pages/home/home.component';
import { ConfiguracoesComponent } from 'src/app/logado/configuracoes/configuracoes.component';
import { LocalizadorComponent } from './logado/localizador/localizador.component';
import { PagInicialLoginComponent } from './logado/pag-inicial-login/pag-inicial-login.component'; 
import { LoginComponent } from 'src/pages/login/login.component';
import { CadastroComponent } from 'src/pages/login/cadastro/cadastro.component';
import { AuthGuard } from './logado/helpers/auth.guard';

const routes: Routes = [
{path: '', component:HomeComponent},
{path: 'login', component:LoginComponent, canActivate: [AuthGuard]},
{path: 'about',component:AboutComponent},
{path: 'contact',component:ContactComponent},
{ path: 'localizador', component: LocalizadorComponent},
{ path: 'config', component: ConfiguracoesComponent},
{path: 'app-pag-inicial-login', component: PagInicialLoginComponent},
{path: 'cadastro', component: CadastroComponent},
{ path: '**', redirectTo: '/'}
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
