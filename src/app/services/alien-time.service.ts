import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AlienTime } from '../models/alien-time';

interface EarthTimeResponse {
  earthTime: String;
}
interface AlarmResponse {
  message: string;
}


@Injectable({
  providedIn: 'root'
})
export class AlienTimeService {

  private apiUrl = 'http://localhost:8080/api/time';

  constructor(private http: HttpClient) { }

  getCurrentAlienTime(): Observable<AlienTime>{
    return this.http.get<AlienTime>(`${this.apiUrl}/currentAlienTime`);
  }

  convertToEarth(alienTime: AlienTime): Observable<EarthTimeResponse> {
    return this.http.post<EarthTimeResponse>(`${this.apiUrl}/convertToEarth`, alienTime);
  }

  setAlarm(alienTime: AlienTime): Observable<AlarmResponse> {
    return this.http.post<AlarmResponse>(`${this.apiUrl}/setAlarm`, alienTime);
  }

  setAlienTime(alienTime: AlienTime): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/setAlienTime`, alienTime);
  }
}
