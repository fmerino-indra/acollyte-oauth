import { Component, OnInit, HostListener } from '@angular/core';

import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { ServiceService } from '../services/service/service.service';
import { PageService } from '../model/page-service';
import { Service } from '../model/service';
import { Person } from '../model/person';

import { faMobileAlt, faAt } from '@fortawesome/free-solid-svg-icons';

import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.css']
})
export class DashboardUserComponent implements OnInit {
  services: Service[] = [];
  cards: any;
  cols = 1;

  faMobileAlt = faMobileAlt;
  faAt = faAt;
  persons: Person[];
  LIST_IDS: string[] = [];
  page = 0;
  restoListName = 'restoList';
  lastPage: PageService;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private serviceService: ServiceService
  ) {}

  ngOnInit() {
    this.callService();
    this.serviceService.findOtherPersons()
       .subscribe(persons => {
         this.persons = persons
       });
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    if (this.bottomReached()) {
      this.callService();
    }
  }

  private callService(): void {
    if (!this.lastPage || !this.lastPage.last) {
      this.serviceService.findFullServices(this.page++, 3)
        .subscribe(pageServices => {
          this.lastPage = pageServices;
          this.services.push(...pageServices.content);
          this.calcCards();
        });
      }
  }
  bottomReached(): boolean {
    return (window.innerHeight + window.scrollY) >= document.body.offsetHeight;
  }

  private calcCards() {
    this.cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
      map(({ matches }) => {
        if (this.services) {
          const retorno = this.services.map(data => {
            const retorno = {
              data,
              cols: 1,
              rows: 1
            }
            return retorno;
          });
          this.cols = 3;
          return retorno;
        } else {
          return null;
        }
      })
    );
  }

  addId(i: number): string {
    // debugger;
    if (i === 0) {
      this.LIST_IDS.length = 0;
      // this.LIST_IDS = [];
    }
    console.log('Valor i=' + i);
    this.LIST_IDS.push('assignedList-' + i);
    return i + '';
  }
  onDrop(event: CdkDragDrop<string[]>) {
    // debugger;
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data,
        event.previousIndex,
        event.currentIndex);
    } else {
      console.log('Lista destino:' + event.container.id);
      const datos = this.services[0].person;
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex, event.currentIndex);
    // transferArrayItem(event.previousContainer.data,
    //   event.container.data,
    //   event.previousIndex, event.currentIndex);
    }
  }
}
  /** Based on the screen size, switch from standard to one column per row */
  // cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
  //   map(({ matches }) => {
  //     if (matches) {
  //       return [
  //         { title: 'Card 1', cols: 1, rows: 1 },
  //         { title: 'Card 2', cols: 1, rows: 1 },
  //         { title: 'Card 3', cols: 1, rows: 1 },
  //         { title: 'Card 4', cols: 1, rows: 1 }
  //       ];
  //     }

  //     return [
  //       { title: 'Card 1', cols: 2, rows: 1 },
  //       { title: 'Card 2', cols: 1, rows: 1 },
  //       { title: 'Card 3', cols: 1, rows: 2 },
  //       { title: 'Card 4', cols: 1, rows: 1 }
  //     ];
  //   })
  // );
  // cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
  //   map(({ matches }) => {
  //     if (this.services) {
  //       const retorno = this.services.map(data => {
  //         debugger;
  //         const retorno = {
  //           data,
  //           cols: 1,
  //           rows: 1
  //         }
  //         return retorno;
  //       });
  //       return retorno;
  //     } else {
  //       return null;
  //     }
  //   })
  // );