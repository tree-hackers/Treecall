import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartTableComponent } from './part-table.component';

describe('PartTableComponent', () => {
  let component: PartTableComponent;
  let fixture: ComponentFixture<PartTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
