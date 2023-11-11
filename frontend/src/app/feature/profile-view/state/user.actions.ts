export namespace UserStateActions {
  export class GetUser {
    static readonly type = '[User] Get user';

    constructor(public id: number) {}
  }
}
