import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AcollyteRoutingModule } from './acollyte-routing.module';
import { OwnServiceComponent } from './own-service/own-service.component';
import { AcollyteHeaderComponent } from './acollyte-header/acollyte-header.component';
import { AcollyteBodyComponent } from './acollyte-body/acollyte-body.component';
import { AcollyteDetailComponent } from './acollyte-detail/acollyte-detail.component';


@NgModule({
  declarations: [OwnServiceComponent, AcollyteHeaderComponent, AcollyteBodyComponent, AcollyteDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    AcollyteRoutingModule
  ]
})
export class AcollyteModule { }
