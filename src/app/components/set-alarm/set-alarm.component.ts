import { Component } from '@angular/core';
import { AlienTime } from '../../models/alien-time';
import { AlienTimeService } from '../../services/alien-time.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AlarmResponse } from '../../models/alarm-response';

@Component({  selector: 'app-set-alarm',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './set-alarm.component.html',
  styleUrl: './set-alarm.component.css'
})
export class SetAlarmComponent {
  alienTime: AlienTime = new AlienTime(2804, 1, 1, 0, 0, 0);
  alarmSetMessage: string = '';

  constructor(private alienTimeService: AlienTimeService) {}

  // setAlarm() {
  //   this.alarmSetMessage = ''; // Reset message on new submission
  //   if (this.isValidAlienTime(this.alienTime)) {
  //     this.alienTimeService.setAlarm(this.alienTime).subscribe(
  //       (message) => {
  //         this.alarmSetMessage = message;
  //       },
  //       (error) => {
  //         console.error('Error setting alarm:', error);
  //         this.alarmSetMessage = 'Failed to set alarm. Please try again.';
  //       }
  //     );
  //   } else {
  //     this.alarmSetMessage = 'Invalid time format. Please enter valid Alien time.';
  //   }
  // }
  
  setAlarm() {
    this.alarmSetMessage = ''; // Reset message on new submission
    console.log('Setting Alarm with Alien Time:', this.alienTime); // Log the alien time

    if (this.isValidAlienTime(this.alienTime)) {
        this.alienTimeService.setAlarm(this.alienTime).subscribe(
            (response: AlarmResponse) => {
                this.alarmSetMessage = response.message; // Use the message from the JSON response
            },
            (error) => {
                console.error('Error setting alarm:', error);
                this.alarmSetMessage = 'Failed to set alarm. Please try again.'; // Handle error
            }
        );
    } else {
        this.alarmSetMessage = 'Invalid time format. Please enter valid Alien time.'; // Invalid input
    }
}

  
  
  isValidAlienTime(alienTime: AlienTime): boolean {
    return (
      alienTime.second >= 0 && alienTime.second < 90 &&
      alienTime.minute >= 0 && alienTime.minute < 90 &&
      alienTime.hour >= 0 && alienTime.hour < 36 &&
      alienTime.day >= 1 && alienTime.day <= this.getDaysInMonth(alienTime.month) &&
      alienTime.month >= 1 && alienTime.month <= 18
    );
  }
  
  // Helper to get days in a given month
  getDaysInMonth(month: number): number { 
    const daysInMonth = [44, 42, 48, 40, 48, 44, 40, 44, 42, 40, 40, 42, 44, 48, 42, 40, 44, 38];
    return daysInMonth[month - 1] || 0;
  }
}
