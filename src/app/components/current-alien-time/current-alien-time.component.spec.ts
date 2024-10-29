import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentAlienTimeComponent } from './current-alien-time.component';

describe('CurrentAlienTimeComponent', () => {
  let component: CurrentAlienTimeComponent;
  let fixture: ComponentFixture<CurrentAlienTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrentAlienTimeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurrentAlienTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
