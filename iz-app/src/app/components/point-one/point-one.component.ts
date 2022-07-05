import { Component, OnInit } from '@angular/core';
import { PointOneService } from 'src/services/pointOne.service';

@Component({
  selector: 'app-point-one',
  templateUrl: './point-one.component.html',
  styleUrls: ['./point-one.component.css']
})
export class PointOneComponent implements OnInit {
  motherboards: string[] = [];

  mbName: any;
  upgrade: any;

  results: any[] = [];

  constructor(private p1s: PointOneService) { }

  ngOnInit(): void {
    this.loadMotherboards()
  }

  loadMotherboards() {
    this.p1s.getAllMotherboards().subscribe(
      res => {
        this.motherboards = res
      }, err => {
        alert('problem with loading motherboards')
      }
    )
  }

  onSubmit() {
    this.p1s.upgrade(this.mbName, this.upgrade).subscribe(
      res => {
        this.results = res
      }, err => {
        alert('problem with finding upgrade for motherboard')
      }
    )
  }

  onChange() {
    this.results = []
  }

}
