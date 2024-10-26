import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowedBikesComponent } from './borrowed-bikes.component';

describe('BorrowedBikesComponent', () => {
  let component: BorrowedBikesComponent;
  let fixture: ComponentFixture<BorrowedBikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BorrowedBikesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BorrowedBikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
