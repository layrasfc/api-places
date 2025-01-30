import { Component, EventEmitter, Input, Output, Signal, signal } from '@angular/core';
import { LineComponent } from '../line/line.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delete-modal',
  imports: [LineComponent, CommonModule],
  templateUrl: './delete-modal.component.html',
  styleUrl: './delete-modal.component.css'
})
export class DeleteModalComponent {
  @Input() isOpen: Signal<boolean> = signal(false);
  @Output() close = new EventEmitter<void>();

  @Input() id: string = "";

  closeModalOutsideClick(event: MouseEvent) {
    const targetElement = event.target as HTMLElement;
    if (targetElement.classList.contains('fixed')) {
      this.close.emit();
    }
  }

  deletePlace(){
    this.close.emit()
  }

}
