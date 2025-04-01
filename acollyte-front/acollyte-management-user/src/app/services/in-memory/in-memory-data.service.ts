import { Injectable } from '@angular/core';
import { Service } from '../../model/service';
import { InMemoryDbService } from 'angular-in-memory-web-api';

import servicesDto from '../../../assets/servicesDto.json';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  constructor() {
    console.log(servicesDto);
  }

  // createDb() {
  //   const services = [
  //     { id: 11, serviceName: 'Dr Nice'},
  //     { id: 12, serviceName: 'Narco'},
  //     { id: 13, serviceName: 'Bombasto' },
  //     { id: 14, serviceName: 'Celeritas' },
  //     { id: 15, serviceName: 'Magneta' },
  //     { id: 16, serviceName: 'RubberMan' },
  //     { id: 17, serviceName: 'Dynama' },
  //     { id: 18, serviceName: 'Dr IQ' },
  //     { id: 19, serviceName: 'Magma' },
  //     { id: 20, serviceName: 'Tornado' }
  //   ];


  //   return {services};
  // }

  createDb() {
    const services = servicesDto;

    return {
      services : services['services'],
      persons: services['persons']
    };
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(services: Service[]): number {
    return services.length > 0 ? Math.max(...services.map(service => service.id)) + 1 : 11;
  }
}
