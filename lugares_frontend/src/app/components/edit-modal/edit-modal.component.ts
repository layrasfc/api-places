import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, Signal, signal } from '@angular/core';
import { LineComponent } from '../line/line.component';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-edit-modal',
  imports: [CommonModule,
    LineComponent, FormsModule],
  templateUrl: './edit-modal.component.html',
  styleUrl: './edit-modal.component.css'
})
export class EditModalComponent {

  @Input() isOpen: boolean = false;
  @Output() close = new EventEmitter<void>();

  @Input() place: string = "";
  @Input() state: string = "";
  @Input() id: string = "";

  


}
