import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntregaAddComponent } from './entrega-add.component';

describe('EntregaAddComponent', () => {
  let component: EntregaAddComponent;
  let fixture: ComponentFixture<EntregaAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntregaAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntregaAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
