import { Component, OnInit } from '@angular/core';
import { RafflePersonDto } from '../model/raffle-person-dto';
import { AcollyteService } from '../services/acollyte.service';

@Component({
  selector: 'app-acollyte-detail',
  templateUrl: './acollyte-detail.component.html',
  styleUrls: ['./acollyte-detail.component.css']
})
export class AcollyteDetailComponent implements OnInit {

  selectedRafflePerson: RafflePersonDto;
  constructor(  private acollyteService: AcollyteService ) { }

  ngOnInit() {
    this.acollyteService.currentMessage.subscribe(message => {
      this.selectedRafflePerson = message;
    })
  }
}
