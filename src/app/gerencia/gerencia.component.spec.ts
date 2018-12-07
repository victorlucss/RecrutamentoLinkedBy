import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciaComponent } from './gerencia.component';

describe('GerenciaComponent', () => {
  let component: GerenciaComponent;
  let fixture: ComponentFixture<GerenciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GerenciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GerenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
