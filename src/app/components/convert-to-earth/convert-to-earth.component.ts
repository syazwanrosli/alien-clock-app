import { Component } from '@angular/core';
import { AlienTime } from '../../models/alien-time';
import { AlienTimeService } from '../../services/alien-time.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-convert-to-earth',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './convert-to-earth.component.html',
  styleUrl: './convert-to-earth.component.css'
})
export class ConvertToEarthComponent {
  alienTime: AlienTime = new AlienTime(2804, 1, 1, 0, 0, 0);
  earthTime!: Date;

  constructor(private alienTimeService: AlienTimeService) {}

  convertToEarth(): void {
    this.alienTimeService.convertToEarth(this.alienTime).subscribe(
      (response) => {
        // Expecting a string format date from backend
        this.earthTime = new Date(response.earthTime  as string); // Convert string to Date
      },
      (error) => {
        console.error('Error converting to Earth Time:', error); // Handle errors
      }
    );
  }  
}
