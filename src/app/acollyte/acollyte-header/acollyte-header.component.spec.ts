import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcollyteHeaderComponent } from './acollyte-header.component';

describe('AcollyteHeaderComponent', () => {
  let component: AcollyteHeaderComponent;
  let fixture: ComponentFixture<AcollyteHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcollyteHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcollyteHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
