import { Component, OnInit, ViewChild } from '@angular/core';
import { SideNavService } from './side-nav.service';
import { MatSidenav } from '@angular/material/sidenav';


@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  @ViewChild(MatSidenav, {static: false}) matSideNav: MatSidenav;

  constructor(
    private sideNavService: SideNavService
  ) { }

  ngOnInit() {
    this.sideNavService.changeSideNav.subscribe(() => {
      this.matSideNav.toggle();
    });
  }


}
