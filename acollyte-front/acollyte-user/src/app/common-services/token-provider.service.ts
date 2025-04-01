import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModel } from '../model/common-model';

@Injectable({
  providedIn: 'root'
})
export class TokenProviderService {
  token: string;

  constructor( private http: HttpClient ) {
    // this.loadJWT();
  }

  loadJWT() {
    return new Promise((resolve, reject) => {
      this.http.get('assets/jwt-sample.txt', {responseType: 'text'})
        .subscribe(
          data => {
            console.log(data);
            this.loadToken(data);
          },
          error => {
            console.log(error);
          }
        );
      resolve(true);
    });
  }

  loadToken(data) {
    let commonModel: CommonModel;
    console.log('Los queryParams');
    console.log(data);
    if (data) {
      this.token = data;
      commonModel = new CommonModel();
      commonModel.assignToken(this.token);
      localStorage.setItem('commonModel', JSON.stringify(commonModel));
    }
  }
}

