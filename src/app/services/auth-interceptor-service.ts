import { HttpErrorResponse, HttpInterceptor, HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { AuthService } from "./authorization-service";
import { Router } from "@angular/router";
import { catchError, throwError } from "rxjs";

export const authInterceptorFn: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);
    const token = authService.getToken();
    const router = inject(Router);
    const publicEndpoints = [
        '/api/authentications/log-in-area',
        '/api/authentications/register-area'
    ];
    const isPublic = publicEndpoints.some((endpoint) => req.url.includes(endpoint));

    console.log('Intercepting request to:', req.url);
    
    if (isPublic) {
        console.log('Public endpoint, no token added.');
        return next(req);
    }

    const authReq = token ? req.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`
        }
    }) : req;

    return next(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
            if (error.status === 401 || error.status === 403) {
                authService.logout();
                router.navigate(['/login']);
                return throwError(() => 'Errore in fase di autenticazione. Ruolo invalido o Sessione scaduta');
            }
            return throwError(() => error);
        })
    );
};