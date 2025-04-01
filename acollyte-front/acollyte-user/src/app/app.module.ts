import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppheaderComponent } from './appheader/appheader.component';
import { AuthModule } from './auth/auth.module';
import { AcollyteModule } from './acollyte/acollyte.module';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { httpInterceptorProviders } from './http-interceptors/index';
import { AppConfigService } from './common-services/app-config.service';
import { TokenProviderService } from './common-services/token-provider.service';

export function servicesOnRun(config: AppConfigService, token: TokenProviderService) {
  return () => config.loadConfig().then(() => token.loadJWT());
}
@NgModule({
  declarations: [
    AppComponent,
    AppheaderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    AuthModule,
    AcollyteModule,

  ],
  providers: [
    httpInterceptorProviders,
    AppConfigService,
    TokenProviderService,
    {
      provide: APP_INITIALIZER,
      useFactory: servicesOnRun,
      multi: true,
      deps: [AppConfigService, TokenProviderService]
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
