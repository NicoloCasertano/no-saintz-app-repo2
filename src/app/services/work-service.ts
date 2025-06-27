import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { WorkModel } from "../models/work-model";
import { map, Observable } from "rxjs";
import { Title } from "@angular/platform-browser";
import { PageResponse } from "../models/page-response-model";

@Injectable({providedIn: 'root'})
export class WorkService {
    private _url: string = 'http://localhost:8080/api/works';
    private _http = inject(HttpClient);

    findAll(): Observable<WorkModel[]> {
        return this._http.get<WorkModel[]>(this._url);
    }

    findByName(title:string ): Observable<WorkModel[]> {
        return this._http.get<PageResponse<WorkModel>>(`${this._url}?title=${title}`).pipe(map(page => page.content));
    }

    findByFilters(queryString:string): Observable<WorkModel[]> {
        return this._http.get<PageResponse<WorkModel>>(`${this._url}${queryString}`).pipe(map(page => page.content));
    }

    findWorkDoneByUser(id:number): Observable<WorkModel[]> {
        return this._http.get<WorkModel[]>(`${this._url}/user/${id}`)
    }
}