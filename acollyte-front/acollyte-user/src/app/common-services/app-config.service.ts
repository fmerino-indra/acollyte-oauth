import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  loadConfig() {
    return new Promise((resolve, reject) => {
      console.log('cuerpo de la promesa de loadConfig()');
      resolve(true);
    });
  }

  constructor() { }
}
