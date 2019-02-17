import { HttpService } from './http.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-visual',
  templateUrl: './visual.component.html',
  styleUrls: ['./visual.component.scss']
})

export class VisualComponent implements OnInit {
  part;
  constructor(
    private _httpService: HttpService
  ) {
    setInterval(() => this.refreshData(), 5000);
  }

  ngOnInit() {
  }

  refreshData() {
    this._httpService.getParts().subscribe((res) => {
      console.log(res)
      for (let i = 0; i < res.length; i++) {
        if (res[i].isBroken) {
          this.part = res[i].name;
        }
      }
    });
  }
}
