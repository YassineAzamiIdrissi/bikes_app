import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecoverSetNewPasswordComponent } from './recover-set-new-password.component';

describe('RecoverSetNewPasswordComponent', () => {
  let component: RecoverSetNewPasswordComponent;
  let fixture: ComponentFixture<RecoverSetNewPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecoverSetNewPasswordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecoverSetNewPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
