import { Component, OnInit } from '@angular/core';

import { SideNavService } from '../side-nav/side-nav.service';

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css']
})
export class ToolBarComponent implements OnInit {

  constructor(
    private sideNavService: SideNavService
  ) { }

  ngOnInit() {
  }

  toggleSideNav(): void {
    this.sideNavService.toggle();
  }
}
