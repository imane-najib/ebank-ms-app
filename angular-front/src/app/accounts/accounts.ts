import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AsyncPipe} from '@angular/common';
import {Account, AccountListState, RequestStatus} from '../model/account.model';
import {catchError, map, Observable, of} from 'rxjs';
import {Loading} from '../services/loading';

@Component({
  selector: 'app-accounts',
  imports: [
    AsyncPipe
  ],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})



export class Accounts {
  private http = inject(HttpClient);
  public loadingService = inject(Loading);

  accounts$: Observable<AccountListState> = this.http
    .get<Account[]>('http://localhost:9999/EBANK-SERVICE/accounts')
    .pipe(
      map(resp => {
        return {
          accounts: resp,
          status: RequestStatus.SUCCESS
        };
      }),
      catchError(err => {
        return of({
          accounts: [],
          status: RequestStatus.ERROR,
          errMessage: err.statusText
        });
      })
    );
  protected readonly RequestStatus = RequestStatus;
}

