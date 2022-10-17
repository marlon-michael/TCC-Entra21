import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagInicialLoginComponent } from './pag-inicial-login.component';

describe('PagInicialLoginComponent', () => {
  let component: PagInicialLoginComponent;
  let fixture: ComponentFixture<PagInicialLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PagInicialLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PagInicialLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
