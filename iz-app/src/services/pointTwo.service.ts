import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Validity } from "src/app/models/validity.model";

@Injectable({
  providedIn: 'root'
})

export class PointTwoService {

  constructor (private http: HttpClient, private router: Router) { }

  private apiUrl = 'http://localhost:11111/two';

  getType(cpuNum: number, memCap: number, power: number, rws: number) {
    return this.http.get<any>(this.apiUrl + '/numbers/' + cpuNum + '/' + memCap + '/' + power + '/' + rws)
  }
}