import { ApplicationRef, Component, OnDestroy, OnInit } from '@angular/core';
import { AlienTime } from '../../models/alien-time';
import { AlienTimeService } from '../../services/alien-time.service';
import { CommonModule } from '@angular/common';
import { first, interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-current-alien-time',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './current-alien-time.component.html',
  styleUrl: './current-alien-time.component.css'
})
export class CurrentAlienTimeComponent implements OnInit, OnDestroy {
  currentAlienTime!: AlienTime;
  private timerSubscription!: Subscription;

  constructor(private alienTimeService: AlienTimeService, private applicationRef: ApplicationRef){}

  ngOnInit(): void {
      this.initializeAlienTime();
      this.applicationRef.isStable.pipe(first((isStable) => isStable)).subscribe(() => {
        this.timerSubscription = interval(500).subscribe(() => this.incrementAlienTime());
      });
  }

  ngOnDestroy(): void {
      this.timerSubscription.unsubscribe();
  }

  // updateTime(): void {
  //   this.alienTimeService.getCurrentAlienTime().subscribe((time: AlienTime) => {
  //     this.currentAlienTime = time;
  //   });

  // Fetch initial alien time from server
  initializeAlienTime(): void {
    this.alienTimeService.getCurrentAlienTime().subscribe((time: AlienTime) => {
      this.currentAlienTime = time;
    });
  }

  incrementAlienTime(): void {
    // Increment logic for Alien time
    this.currentAlienTime.second++;
    if (this.currentAlienTime.second >= 90) {
      this.currentAlienTime.second = 0;
      this.currentAlienTime.minute++;
    }
    if (this.currentAlienTime.minute >= 90) {
      this.currentAlienTime.minute = 0;
      this.currentAlienTime.hour++;
    }
    if (this.currentAlienTime.hour >= 36) {
      this.currentAlienTime.hour = 0;
      this.currentAlienTime.day++;
    }

  }

}
