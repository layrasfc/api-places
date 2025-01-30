import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LineComponent } from '../line/line.component';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-add-modal',
  imports: [CommonModule, LineComponent, FormsModule ],
  templateUrl: './add-modal.component.html',
  styleUrl: './add-modal.component.css'
})
export class AddModalComponent {
  @Input() isOpen: boolean = false;
  @Output() close = new EventEmitter<void>();

  closeModalOutsideClick(event: MouseEvent) {
    const targetElement = event.target as HTMLElement;
    if (targetElement.classList.contains('fixed')) {
      this.close.emit();
    }
  }

  addPlace(){
    this.close.emit()
  }
}
