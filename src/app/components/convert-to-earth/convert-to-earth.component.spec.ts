import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvertToEarthComponent } from './convert-to-earth.component';

describe('ConvertToEarthComponent', () => {
  let component: ConvertToEarthComponent;
  let fixture: ComponentFixture<ConvertToEarthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConvertToEarthComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConvertToEarthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
