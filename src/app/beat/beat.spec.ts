import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Beat } from './beat';

describe('Beat', () => {
  let component: Beat;
  let fixture: ComponentFixture<Beat>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Beat]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Beat);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
