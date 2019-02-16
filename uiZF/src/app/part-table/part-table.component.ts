import { ConfirmDialogComponent } from './../confirm-dialog/confirm-dialog.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';


@Component({
  selector: 'app-part-table',
  templateUrl: './part-table.component.html',
  styleUrls: ['./part-table.component.scss']
})

export class PartTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'batch', 'partID', 'broken'];
  dataSource;
  constructor(
    public dialog: MatDialog
  ) { }

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
      if (result) {
        console.log('do something backendy here');
      }
    });
  }

  fillTable() {
    this.dataSource;
  }
}
