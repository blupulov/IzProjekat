import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointFourComponent } from './point-four.component';

describe('PointFourComponent', () => {
  let component: PointFourComponent;
  let fixture: ComponentFixture<PointFourComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointFourComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PointFourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
