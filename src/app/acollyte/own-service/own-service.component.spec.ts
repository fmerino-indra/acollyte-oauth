import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnServiceComponent } from './own-service.component';

describe('OwnServiceComponent', () => {
  let component: OwnServiceComponent;
  let fixture: ComponentFixture<OwnServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
