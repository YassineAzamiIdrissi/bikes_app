import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecoverPassEmailComponent } from './recover-pass-email.component';

describe('RecoverPassEmailComponent', () => {
  let component: RecoverPassEmailComponent;
  let fixture: ComponentFixture<RecoverPassEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecoverPassEmailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecoverPassEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
