import { Component, OnInit } from '@angular/core';
import { PointOneService } from 'src/services/pointOne.service';
import { PointTwoService } from 'src/services/pointTwo.service';

@Component({
  selector: 'app-point-two',
  templateUrl: './point-two.component.html',
  styleUrls: ['./point-two.component.css']
})

export class PointTwoComponent implements OnInit {
  cpus: any[] = [];
  rams: any[] = [];
  ps: any[] = [];
  storages: any = [];

  data: any = {corsNum: 0, power: 0, memory: 0, rws: 0};

  type: any = {homePC: 0, businessPC: 0, gamingPC: 0, miningPC: 0}
  //validity: Validity[] = [];
  
  constructor(private pointOneService: PointOneService, private pointTwoService: PointTwoService) { }

  ngOnInit(): void {
    this.loadCpus()
    this.loadRams()
    this.loadStorages()
    this.loadPS()
  }

  loadCpus() {
    this.pointOneService.getAllCpus().subscribe(
      res => {
        this.cpus = res
      }, err => {
        alert('problem with loading cpus')
      }
    )
  }

  loadRams() {
    this.pointOneService.getAllRams().subscribe(
      res => {
        this.rams = res
      }, err => {
        alert('problem with loading rams')
      }
    )
  }

  loadPS() {
    this.pointOneService.getAllPowerSupplies().subscribe(
      res => {
        this.ps = res
      }, err => {
        alert('problem with loading ps')
      }
    )
  }

  loadStorages() {
    this.pointOneService.getAllStorage().subscribe(
      res => {
        this.storages = res
      }, err => {
        alert('problem with loading storages')
      }
    )
  }

  onSubmit() {
    this.pointTwoService.getType(this.data.corsNum, this.data.memory, this.data.power, this.data.rws)
    .subscribe(
      res => {
        this.type = res
      }, err => {
        alert('problem with loading  computer type')
      }
    )
  }
}