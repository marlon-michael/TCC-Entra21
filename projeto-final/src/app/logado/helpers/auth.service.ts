import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from 'types/types';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    cadastro(value: string | null | undefined, value1: string | null | undefined, value2: string | null | undefined, value3: string | null | undefined, value4: string | null | undefined, value5: string | null | undefined) {
      throw new Error('Method not implemented.');
    }
    private userSubject: BehaviorSubject<User | null>;
    public user: Observable<User | null>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<User | null>(localStorage.getItem('user') != null ? JSON.parse( localStorage.getItem('user')!): null);
        this.user = this.userSubject.asObservable();
    }

    public get userValue(): User {
        return this.userSubject.value!;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`/pessoa/login`, { username, password })
            .pipe(map(user => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                user.authdata = window.btoa(username + ':' + password);
                localStorage.setItem('user', JSON.stringify(user));
                this.userSubject.next(user);
                return user;
            }));
    }
    TaLogado(){
        return localStorage.getItem('token')!=null;
    }
    GetToken(){
        return localStorage.getItem('token')||'';
    }
// HaveAccess(){
//     var loggintoken= localStorage.getItem('token')||'';
//     var _extractedtoken=loggintoken.split('.')[1];
//     var _atobdata=atob(_extractedtoken);
//     var _finaldata=JSON.parse(_atobdata);
//         if(_finaldata.role =='admin'){
//             return true;
//         } 
//         // alert('Você não tem acesso')
//             return false;
       
//     // console.log(_finaldata);
    
// }




    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('user');
        this.userSubject.next(null);
        this.router.navigate(['']);
    }
}