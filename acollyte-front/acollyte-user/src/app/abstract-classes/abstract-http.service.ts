import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
// import { CommonModel } from './model/common-model';

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractHttpService {

  constructor() { }

  protected log(message: any | object[]): void;

  /** Log a HeroService message with the MessageService */
    protected log(message: object | any[]): void {
      if ( typeof message === 'string' ) {
        console.log(`${this.constructor.name} : ${message}`);
      } else if ( Array.isArray(message)) {
        message.forEach(el => {
          this.log(el);
        })
      }
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    protected handleError<T>(operation = 'operation', result?: T) {
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
