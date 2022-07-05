import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointThreeComponent } from './point-three.component';

describe('PointThreeComponent', () => {
  let component: PointThreeComponent;
  let fixture: ComponentFixture<PointThreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointThreeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PointThreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
