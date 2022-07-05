import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class PointOneService {

  constructor (private http: HttpClient, private router: Router) { }

  private apiUrl = 'http://localhost:11111/one';

  getAllCpus() {
    return this.http.get<any>(this.apiUrl + '/cpu')
  }

  getAllRams() {
    return this.http.get<any>(this.apiUrl + '/ram')
  }

  getAllPowerSupplies() {
    return this.http.get<any>(this.apiUrl + '/powerSupply')
  }

  getAllStorage() {
    return this.http.get<any>(this.apiUrl + '/storage')
  }

  getAllMotherboards() {
    return this.http.get<any>(this.apiUrl + '/motherboard')
  }

  upgrade(mbName: string, upgrade: string) {
    if(upgrade == 'ram')
      return this.http.get<any>(this.apiUrl + '/ram/' + mbName + '/' + 'a')
    else
      return this.http.get<any>(this.apiUrl + '/cpu/' + mbName + '/' + 'a')
  }
}