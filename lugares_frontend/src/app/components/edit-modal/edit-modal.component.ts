import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, Signal, signal } from '@angular/core';
import { LineComponent } from '../line/line.component';

@Component({
  selector: 'app-edit-modal',
  imports: [CommonModule,
    LineComponent
  ],
  templateUrl: './edit-modal.component.html',
  styleUrl: './edit-modal.component.css'
})
export class EditModalComponent {

  @Input() isOpen: Signal<boolean> = signal(false);
  @Output() close = new EventEmitter<void>();


}
