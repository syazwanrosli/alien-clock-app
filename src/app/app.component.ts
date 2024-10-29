import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { CurrentAlienTimeComponent } from './components/current-alien-time/current-alien-time.component';
import { ConvertToEarthComponent } from './components/convert-to-earth/convert-to-earth.component';
import { SetAlarmComponent } from './components/set-alarm/set-alarm.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CurrentAlienTimeComponent, ConvertToEarthComponent, SetAlarmComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'alien-clock-frontend';
}
