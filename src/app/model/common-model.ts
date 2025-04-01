import { Usertoken } from './user-token';

export class CommonModel {
    userToken: Usertoken;

    assignToken(token: string) {
        if (!this.userToken) {
            this.userToken = new Usertoken();
        }
        this.userToken.assignToken(token);
    }
}
