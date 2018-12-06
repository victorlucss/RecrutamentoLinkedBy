import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/retry';
import { IServerResponse } from '../../Interfaces/server-response.interface';
import { Observable } from 'rxjs/Rx';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  getProdutos(): Observable<IServerResponse[]> {
    return this.http.get<IServerResponse[]>('http://localhost:8080/produto');
  }
}
