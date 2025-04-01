import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcollyteBodyComponent } from './acollyte-body.component';

describe('AcollyteBodyComponent', () => {
  let component: AcollyteBodyComponent;
  let fixture: ComponentFixture<AcollyteBodyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcollyteBodyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcollyteBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
