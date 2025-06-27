import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/authorization-service';
import { Router, RouterModule } from '@angular/router';

@Component({
    selector: 'app-log-in-area',
    standalone: true,
    imports: [ReactiveFormsModule, RouterModule],
    templateUrl: './log-in-area.html',
    styleUrl: './log-in-area.css'
})
export class LogInArea {
    formBuilder = inject(FormBuilder);
    private _authService = inject(AuthService);
    private _router = inject(Router);

    loginForm: FormGroup = this.formBuilder.group({
        email:["", [Validators.required]],
        password:["", [Validators.required]]
    });
    
    onSubmit() {
        this._authService.login(this.loginForm.value);
        this._router.navigate(['/home']);
    }
    
}
