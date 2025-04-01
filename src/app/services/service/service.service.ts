import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { PageService } from '../../model/page-service';
import { Service } from '../../model/service';
import { Person } from '../../model/person';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  // private serviceUrl = 'https://acolyte-calendar.herokuapp.com/services';  // URL to web api
  // private serviceUrl = 'api/services';  // URL to web api
  private personUrl = 'api/persons';  // URL to web api
  private serviceUrl = 'https://acolyte-calendar.herokuapp.com/services';  // URL to web api

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  constructor(
    private http: HttpClient
  ) { }

  findFullServices(page: number, size: number): Observable<PageService> {
    let params = new HttpParams();

    params = params.append('page', `${page}`);
    params = params.append('size', `${size}`);
    return this.http.get<PageService>(this.serviceUrl, {
      params
      })
      .pipe(
        tap(_ => {
          this.log('ServiceService: fetched services');
        }),
        catchError(this.handleError<PageService>('findFullServices'))
      );
  }
  findOtherPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(this.personUrl)
      .pipe(
        tap(_ => this.log('ServiceService: fetched persons')),
        catchError(this.handleError<Person[]>('findOtherPersons', []))
      );
  }


  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(`ServiceService: ${message}`);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
