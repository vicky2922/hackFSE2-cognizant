import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-display-module',
  templateUrl: './display-module.component.html',
  styleUrls: ['./display-module.component.css']
})
export class DisplayModuleComponent implements OnInit {

  @Input() stockData: any;
  @Input() companyData:any;

  constructor() { }
  ngOnInit(): void {
  }
}
