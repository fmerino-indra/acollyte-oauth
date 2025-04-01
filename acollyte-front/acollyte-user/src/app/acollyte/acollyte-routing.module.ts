import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OwnServiceComponent } from './own-service/own-service.component';
// import { AcollyteHeaderComponent } from './acollyte-header/acollyte-header.component';
import { AcollyteBodyComponent } from './acollyte-body/acollyte-body.component';
import { AcollyteDetailComponent } from './acollyte-detail/acollyte-detail.component';

const acollyteRoutes: Routes = [
    {
        path: 'acollyte',
        component: AcollyteBodyComponent,
        children: [
            {
                path: '',
                children: [
                    {
                        path: '',
                        component: OwnServiceComponent
                    },
                    {
                        path: 'detail',
                        component: AcollyteDetailComponent
                    }
                ]
            }
        ]

/*
        ,
        canActivate: [AuthGuard]
*/
    }
];

@NgModule({
  imports: [RouterModule.forChild(acollyteRoutes)],
  exports: [RouterModule]
})
export class AcollyteRoutingModule { }
