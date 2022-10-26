import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

constructor(private authenticationservice: AuthenticationService,
  private route: Router){

}

  canActivate() {
  if(this.authenticationservice.TaLogado())
    return true;
    else{
      this.route.navigate(['']);
      return false;
    }
  }
  
}
