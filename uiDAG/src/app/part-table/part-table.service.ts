import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import {iPart } from './iPart';

@Injectable({
  providedIn: 'root'
})
export class PartTableService {

  url = environment.apiServer.url;

  constructor(
    private _http: HttpClient
  ) { }

  getParts(): Observable<any> {
    return this._http.get(this.url);
  }

  setBroken(part): Observable<any> {
    return this._http.put<any>(this.url + '/set-broken/' + part, '');
  }
  
  parsePart(json: any): iPart {
    const part: iPart = <iPart>json;
    return part;
  }
  
  convertParts(res): Array<iPart> {
    const parts: Array<iPart> = [];
      for (let i = 0; i < res.length; i++) {
      const part: iPart = this.parsePart(res[i]);
      parts.push(part);
    }
    return parts;
  }

}
