import { ConfirmDialogComponent } from './confirm-dialog.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatButtonModule } from '@angular/material';

@NgModule({
  declarations: [
    ConfirmDialogComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule
  ],
  exports: [
    ConfirmDialogComponent
  ]
})
export class ConfirmDialogModule { }
