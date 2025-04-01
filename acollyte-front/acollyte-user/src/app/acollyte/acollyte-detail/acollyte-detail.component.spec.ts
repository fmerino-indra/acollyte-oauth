import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcollyteDetailComponent } from './acollyte-detail.component';

describe('AcollyteDetailComponent', () => {
  let component: AcollyteDetailComponent;
  let fixture: ComponentFixture<AcollyteDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcollyteDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcollyteDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
