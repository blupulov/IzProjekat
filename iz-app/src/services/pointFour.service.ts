import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class PointFourService {

  constructor (private http: HttpClient, private router: Router) { }

  private apiUrl = 'http://localhost:11111/four';
  
  getSimilarity(cn: number, cf: number, cc: number, cb: string, rt: string, rc: number, rf: number, st: string, rws: number, sc: number) {
    return this.http.get<any>(this.apiUrl + '/similarity/' + cn + '/' + cf + '/' + cc + '/' + cb + 
      '/' + rt + '/' + rc + '/' + rf + '/' + st + '/' + rws + '/' + sc )
  }
}