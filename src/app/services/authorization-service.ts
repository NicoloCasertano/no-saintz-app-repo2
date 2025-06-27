import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { RouteConfigLoadEnd, Router } from "@angular/router";
import { jwtDecode } from 'jwt-decode';
import { RegistrationRequestModel } from "../models/registration-request-model";
import { Observable } from "rxjs";
import { JwtTokenModel } from "../models/jwt-token-model";
import { LoginRequestModel } from "../models/login-request-model";
import { JwtPayloadModel } from "../models/jwt-payload-model";

@Injectable({providedIn: 'root'})
export class AuthService {

    private _url: string = 'https://localhost:8080/api/auth';
    private _http = inject(HttpClient);
    private _router = inject(Router);

    register(regreq:RegistrationRequestModel): Observable<JwtTokenModel> {
        return this._http.post<JwtTokenModel>(`${this._url}/register`,regreq);
    }

    login(logreq: LoginRequestModel) {
        this._http.post<JwtTokenModel>(`${this._url}/login`,logreq).subscribe({
            next: (authToken) =>{
                localStorage.setItem("jwt",authToken.token );
                console.log("Authenticated as "+ logreq.email);
                this._router.navigate(['/home']);
            },
            error: e => alert('Errore nel accesso')
        });
    }

    logout() {
        localStorage.removeItem('jwt');
        console.log("Logged out");
    }

    isLogged(): boolean {
        return localStorage.getItem('jwt')!= null;
    }

    getToken():string|null {
        return localStorage.getItem("jwt");
    }

    getUserId():string|null {
        const token = this.getToken();
        if(!token) {
            return null;
        }
        const decoded = jwtDecode<JwtPayloadModel>(token);
        return decoded.userId || null;
    }
}