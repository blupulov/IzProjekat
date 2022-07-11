import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class PointThreeService {

  constructor (private http: HttpClient, private router: Router) { }

  private apiUrl = 'http://localhost:11111/three';
  
  getProbability(nodeName : string) {
    return this.http.get<any>(this.apiUrl + '/probability/' + nodeName)
  }
}