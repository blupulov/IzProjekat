import { Component, OnInit } from '@angular/core';
import { PointThreeService } from '../../../services/pointThree.service'; 
import { Validity } from '../../models/validity.model'
@Component({
  selector: 'app-point-three',
  templateUrl: './point-three.component.html',
  styleUrls: ['./point-three.component.css']
})
export class PointThreeComponent implements OnInit {

  probabilities: Validity[]= [];
  input: any;

  constructor(private service : PointThreeService) { }

  ngOnInit(): void {
  }
  onSubmit() {
    this.service.getProbability(this.input).subscribe(
      res => {
        this.probabilities = res
      }, err => {
        alert('The selected problem is not supported')
      }
    )
  }

}
