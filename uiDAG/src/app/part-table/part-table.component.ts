import { ConfirmDialogComponent } from './../confirm-dialog/confirm-dialog.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { PartTableService } from './part-table.service';

@Component({
  selector: 'app-part-table',
  templateUrl: './part-table.component.html',
  styleUrls: ['./part-table.component.scss']
})

export class PartTableComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'batch'];
  dataSource;
  constructor(
    public dialog: MatDialog,
    private _partTableService: PartTableService
  ) {
    this.fillTable();
    setInterval(() => this.fillTable(), 5000);
  }

  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  click(val) {
    this.openDialog(val);
  }

  openDialog(val): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: { val }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result)
      if (result) {
        this._partTableService.setBroken(result.val.id).subscribe((res) => {
        });
      }
    });
  }

  fillTable() {
    this._partTableService.getParts().subscribe((res) => {
      this.dataSource = new MatTableDataSource(this._partTableService.convertParts(res));
    });
  }
}
