import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PersonDto } from '../model/person-dto';
import { AcollyteService } from '../services/acollyte.service';

@Component({
  selector: 'app-acollyte-header',
  templateUrl: './acollyte-header.component.html',
  styleUrls: ['./acollyte-header.component.css']
})
export class AcollyteHeaderComponent implements OnInit {

  myInfo$: Observable<PersonDto>;
  
  constructor( private acollyteService: AcollyteService ) { }

  ngOnInit() {
    this.myInfo();
  }

  myInfo() {
    this.myInfo$ = this.acollyteService.myInfo();
  }

}
