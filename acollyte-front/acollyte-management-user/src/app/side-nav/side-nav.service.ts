import { Injectable, Output, EventEmitter } from '@angular/core'

@Injectable({
  providedIn: 'root'
})
export class SideNavService {

  @Output() changeSideNav: EventEmitter<any> = new EventEmitter();

  constructor() { }

  toggle(): void {
    this.changeSideNav.emit();
  }
}
