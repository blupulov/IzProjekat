import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PointFourService } from 'src/services/pointFour.service';

@Component({
  selector: 'app-point-four',
  templateUrl: './point-four.component.html',
  styleUrls: ['./point-four.component.css']
})
export class PointFourComponent implements OnInit {
  //similarity/{cn}/{cf}/{cc}/{cb}/{rt}/{rc}/{rf}/{st}/{rws}/{sc}
  cn: number = 0;
  cf: number = 0;
  cc: number = 0;
  cb: string = '';
  rt: string = '';
  rc: number = 0;
  rf: number = 0;
  st: string = '';
  rws: number = 0;
  sc: number = 0;

  similarComps: any[] = [];

  constructor(private p4s: PointFourService, private router: Router) { }

  ngOnInit(): void { }

  onSubmit() {
    this.p4s.getSimilarity(this.cn, this.cf, this.cc, this.cb, this.rt, this.rc, this.rf, this.st, this.rws, this.sc).subscribe(
      res => {
        this.similarComps = res
      }, err => {
        alert('problem with loading similar pc-s')
      }
    )
  }
}
