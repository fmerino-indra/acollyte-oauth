import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject } from 'rxjs';

import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { HELLO_ACOLLYTE, LIST_ACOLLYTE, MY_INFO, DETAIL_ACOLLYTE } from '../../constants/constants';
import { CommonModel } from '../../model/common-model';
import { AbstractHttpService } from 'src/app/abstract-classes/abstract-http.service';
import { RafflePersonDto } from '../model/raffle-person-dto';
import { PersonDto } from '../model/person-dto';


@Injectable({
    providedIn: 'root'
})
export class AcollyteService extends AbstractHttpService  {
    private selectedRafflePerson: RafflePersonDto;

    private messageSource = new BehaviorSubject(this.selectedRafflePerson);
    currentMessage = this.messageSource.asObservable();

    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(
        private http: HttpClient
    ) {
        super();
    }

    sayHelloService(): Observable<string> {
        // With Params
        // const parametros = new HttpParams()
        //     .set('userId', '7');
        // return this.http.get<string>(HELLO_ACOLLYTE, {
        //     params: parametros,
        //     responseType: 'text' as 'json',
        //     withCredentials: true
        // })
        // With path variable
        return this.http.get<string>(HELLO_ACOLLYTE, { responseType: 'text' as 'json'  })
            .pipe(
                tap(event => {

                    this.log(event);
                }),
                catchError(
                    this.handleError<string>('AcollyteService: Say Hello', '')
                )
            );
    }
    listRafflePerson(): Observable<RafflePersonDto[]> {
        return this.http.get<RafflePersonDto[]>(LIST_ACOLLYTE, { responseType: 'json' })
            .pipe(
                tap(event => {
                    this.log(event);
                }),
                catchError(
                    this.handleError<RafflePersonDto[]>('AcollyteService: Say Hello', null)
                )
            );
    }

    detailRafflePerson(rpId: number): Observable<RafflePersonDto[]> {
        this.log(DETAIL_ACOLLYTE);
        return this.http.get<RafflePersonDto[]>(DETAIL_ACOLLYTE, { responseType: 'json' })
            .pipe(
                tap(event => {
                    this.log(event);
                }),
                catchError(
                    this.handleError<RafflePersonDto[]>('AcollyteService: Say Hello', null)
                )
            );
    }

    myInfo(): Observable<PersonDto> {
        return this.http.get<PersonDto>(MY_INFO, { responseType: 'json' })
            .pipe(
                tap(event => {
                    this.log(event);
                }),
                catchError(
                    this.handleError<PersonDto>('AcollyteService: Say Hello', null)
                )
            );
    }

    setSelectRaffle(rafflePerson: RafflePersonDto) {
      this.selectedRafflePerson = rafflePerson;
    }

    getSelectedRaffle(): RafflePersonDto {
        return this.selectedRafflePerson;
    }

    changeSelected(rafflePerson: RafflePersonDto) {
        this.setSelectRaffle(rafflePerson);
        this.messageSource.next(rafflePerson);
    }
}
