import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { finalize, tap } from 'rxjs/operators';

import { CommonModel } from '../model/common-model';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class FakeJwtBearerInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const started = Date.now();
    let ok: string;
    let request = req;
    const commonModel: CommonModel = JSON.parse(localStorage.getItem('commonModel'));
    let token: string;

    console.log('Intercepting:' + req.url);

    if (req.url.match(/assets\/jwt/)) {
      return next.handle(req);
    }

    if (commonModel.userToken) {
        token = commonModel.userToken.token;
    } else {
        return next.handle(req);
    }

    if (token) {
      // setHeaders: append headers in clone
      // headers: override headers in clone
        request = req.clone({
            setHeaders: {
                authorization: `Bearer ${ token }`
            }
        });
    }

    // extend server response observable with logging
    return next.handle(request)
    .pipe(
        tap(
          // Succeeds when there is a response; ignore other events
          event => {
            ok = event instanceof HttpResponse ? 'succeeded' : '';
          },
          // Operation failed; error is an HttpErrorResponse
          error => ok = 'failed'
        ),
        // Log when response observable either completes or errors
        finalize(() => {
          const elapsed = Date.now() - started;
          const msg = `${req.method} "${req.urlWithParams}"
             ${ok} in ${elapsed} ms.`;
          this.log(msg);
        })
    );
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(`${this.constructor.name} : ${message}`);
    // console.log('Message:', message);
  }

  private samlFlow() {

  }
}
