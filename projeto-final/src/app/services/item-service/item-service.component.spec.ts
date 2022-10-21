import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemServiceComponent } from './item-service.component';

describe('ItemServiceComponent', () => {
  let component: ItemServiceComponent;
  let fixture: ComponentFixture<ItemServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemServiceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
