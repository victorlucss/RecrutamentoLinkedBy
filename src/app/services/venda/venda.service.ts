import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/retry';
import { IServerResponse } from '../../Interfaces/server-response.interface';
import { Observable } from 'rxjs/Rx';
import { Compra } from 'src/app/Classes/compra';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getVendas(): Observable<IServerResponse[]> {
    return this.http.get<IServerResponse[]>(this.baseUrl+'venda');
  }

  darBaixa(compras: Compra[]): Promise<any>{
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrl+'venda/dar-baixa', compras,{ headers: headers } ).toPromise();
  }
}
