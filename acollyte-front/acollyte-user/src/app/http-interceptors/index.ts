/* "Barrel" of Http Interceptors */
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { NoopInterceptor } from './noop-interceptor';
import { FakeJwtBearerInterceptor } from './fake-jwt-bearer-interceptor';
import { UserIdInterceptor } from './userId-interceptor';

/** Http interceptor providers in outside-in order */
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: FakeJwtBearerInterceptor, multi: true },
  { provide: HTTP_INTERCEPTORS, useClass: UserIdInterceptor, multi: true }
  // { provide: HTTP_INTERCEPTORS, useClass: NoopInterceptor, multi: true },
];