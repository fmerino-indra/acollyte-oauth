import { Component, OnInit } from '@angular/core';
import { AcollyteService } from '../services/acollyte.service';
import { RafflePersonDto } from '../model/raffle-person-dto';

@Component({
  selector: 'app-acollyte-body',
  templateUrl: './acollyte-body.component.html',
  styleUrls: ['./acollyte-body.component.css']
})
export class AcollyteBodyComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
