import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntregasItemDetalhesComponent } from './entregas-item-detalhes.component';

describe('EntregasItemDetalhesComponent', () => {
  let component: EntregasItemDetalhesComponent;
  let fixture: ComponentFixture<EntregasItemDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntregasItemDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntregasItemDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
