import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { finalize, tap } from 'rxjs/operators';

import { CommonModel } from '../model/common-model';
import { AbstractInterceptor } from './abstract-interceptor';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class UserIdInterceptor extends AbstractInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const started = Date.now();
    let ok: string;

    let dupReq = req;
    let commonModel: CommonModel;
    let userId: string;
    let laUrl: string;

    this.log('Intercepting:' + req.url);

    if (req.url.match(/assets\/jwt/)) {
      return next.handle(req);
    }

    commonModel = JSON.parse(localStorage.getItem('commonModel'));
    if (commonModel.userToken) {
      userId = commonModel.userToken.id;
    } else {
        return next.handle(req);
    }

    laUrl = req.url;
    this.log(laUrl);
    const regexp = /acollyte/;

    if (laUrl.match(regexp)) {
      laUrl = req.url.replace(/acollyte/, `acollyte/${userId}`);
      const newHttpRequest = req.clone({url: laUrl});


      // newHttpRequest = new HttpRequest( req.method as any, laUrl);
      // newHttpRequest = Object.assign(req, newHttpRequest);

      dupReq = newHttpRequest.clone();
    }

    // Include userId in url as a PathParam

    return next.handle(dupReq)
    .pipe(
        tap(
          // Succeeds when there is a response; ignore other events
          event => {

            ok = event instanceof HttpResponse ? 'succeeded' : ''
          },
          // Operation failed; error is an HttpErrorResponse
          _ => ok = 'failed'
          // error => ok = 'failed'
        ),
        // Log when response observable either completes or errors
        finalize(() => {
          const elapsed = Date.now() - started;
          const msg = `${req.method} "${req.urlWithParams}" ${ok} in ${elapsed} ms.`;
          this.log(msg);
        })
    );
  }
}
