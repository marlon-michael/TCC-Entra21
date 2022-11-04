import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizadorComponent } from './localizador.component';

describe('LocalizadorComponent', () => {
  let component: LocalizadorComponent;
  let fixture: ComponentFixture<LocalizadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocalizadorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocalizadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
