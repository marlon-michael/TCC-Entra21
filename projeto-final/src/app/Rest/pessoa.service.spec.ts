import { TestBed } from '@angular/core/testing';

import { cadastroService } from './cadastro.service';

describe('CadastroService', () => {
  let service: cadastroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(cadastroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});