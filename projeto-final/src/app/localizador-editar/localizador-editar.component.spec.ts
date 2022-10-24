import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizadorEditarComponent } from './localizador-editar.component';

describe('LocalizadorEditarComponent', () => {
  let component: LocalizadorEditarComponent;
  let fixture: ComponentFixture<LocalizadorEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocalizadorEditarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocalizadorEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
