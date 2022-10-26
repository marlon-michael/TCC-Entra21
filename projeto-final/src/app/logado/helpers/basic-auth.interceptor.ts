import { Injectable, Injector } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from './auth.service';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService,
        private inject: Injector) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authenticationService=this.inject.get(AuthenticationService);
        let jwtToken = request.clone({
            setHeaders:{
                Authorization: 'bearer'+ authenticationService.GetToken()
            }
                    });
                    return next.handle(jwtToken);
        // add header with basic auth credentials if user is logged in and request is to the api url
        // const user = this.authenticationService.userValue;
        // const isLoggedIn = user && user.authdata;
        // const isApiUrl = !request.url.startsWith('http');
        // if (isLoggedIn && isApiUrl) {
        //     request = request.clone({
        //         setHeaders: { 
        //             Authorization: `Basic ${user.authdata}`
        //         }
        //     });
        // }

        // return next.handle(request);
    }
}