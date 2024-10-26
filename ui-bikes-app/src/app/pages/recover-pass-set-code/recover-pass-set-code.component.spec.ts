import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecoverPassSetCodeComponent } from './recover-pass-set-code.component';

describe('RecoverPassSetCodeComponent', () => {
  let component: RecoverPassSetCodeComponent;
  let fixture: ComponentFixture<RecoverPassSetCodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecoverPassSetCodeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecoverPassSetCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
