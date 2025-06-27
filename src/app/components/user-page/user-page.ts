import { HttpParams } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-page',
  standalone: true,
  imports: [],
  templateUrl: './user-page.html',
  styleUrl: './user-page.css'
})
export class UserPage {
  private _router = inject(ActivatedRoute);

  userId!: string;

  ngOnIniti() {
    this._router.paramMap.subscribe(params => {
      this.userId = params.get('id')!;
      console.log('User corrente: ', this.userId);
    });
  }
}
