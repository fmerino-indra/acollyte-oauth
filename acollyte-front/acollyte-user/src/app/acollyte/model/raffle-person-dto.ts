export class RafflePersonDto {
    personId: number;
    rafflePersonId: number;
    raffleId: number;

    name: string;
    email: string;
    phoneNumber: string;
    comunidad: number;

    serviceDate: Date;
    serviceName: string;

    canGo: boolean;
    haveGone: boolean;
}
