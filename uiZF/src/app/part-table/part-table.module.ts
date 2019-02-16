import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartTableComponent } from './part-table.component';
import { MatTableModule, MatInputModule, MatSortModule,  } from '@angular/material';

@NgModule({
  declarations: [PartTableComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatSortModule,
  ],
  exports: [
    PartTableComponent
  ]
})
export class PartTableModule { }
