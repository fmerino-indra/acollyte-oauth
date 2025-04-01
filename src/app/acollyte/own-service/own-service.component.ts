import { Component, OnInit } from '@angular/core';

import { AcollyteService } from '../services/acollyte.service';
import { Observable, of } from 'rxjs';
import { RafflePersonDto } from '../model/raffle-person-dto';
import { PersonDto } from '../model/person-dto';

@Component({
  selector: 'app-own-service',
  templateUrl: './own-service.component.html',
  styleUrls: ['./own-service.component.css']
})
export class OwnServiceComponent implements OnInit {

  selectedRafflePerson: RafflePersonDto;
  listRafflePerson: RafflePersonDto[];

  constructor( private acollyteService: AcollyteService ) { }

  ngOnInit() {
    this.listRafflePersonService();
  }

  listRafflePersonService() {
    this.acollyteService.listRafflePerson()
    .subscribe( rafflePerson => {
      this.listRafflePerson = rafflePerson;
    });
  }

  selectRaffle(rafflePerson: RafflePersonDto) {
    this.selectedRafflePerson = rafflePerson;
    this.acollyteService.changeSelected(rafflePerson);
  }
}
