import { Component, EventEmitter, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditModalComponent } from '../edit-modal/edit-modal.component';
import { DeleteModalComponent } from '../delete-modal/delete-modal.component';

@Component({
  selector: 'app-table',
  imports: [CommonModule, EditModalComponent, DeleteModalComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  items: any[] = [];

  constructor() {
    this.loadData(); // Carregar os dados na inicialização
  }

  loadData(){
    this.items = [
    { id: 1, place: "Hortolândia", state: "São Paulo", created: "11/02/2024", updated: "11/02/2024 - 09h30" },
    { id: 2, place: "Campinas", state: "São Paulo", created: "12/02/2024", updated: "12/02/2024 - 09h30" },
    { id: 3, place: "Rio de Janeiro", state: "Rio de Janeiro", created: "13/02/2024", updated: "13/02/2024 - 09h30" },
    { id: 4, place: "Belo Horizonte", state: "Minas Gerais", created: "14/02/2024", updated: "14/02/2024 - 09h30" },
    { id: 5, place: "Curitiba", state: "Paraná", created: "15/02/2024", updated: "15/02/2024 - 09h30" },
    { id: 6, place: "Salvador", state: "Bahia", created: "16/02/2024", updated: "16/02/2024 - 09h30" },
    { id: 7, place: "Fortaleza", state: "Ceará", created: "17/02/2024", updated: "17/02/2024 - 09h30" },
    { id: 8, place: "Manaus", state: "Amazonas", created: "18/02/2024", updated: "18/02/2024 - 09h30" }
];}


  // Edit Modal config
  isEditModalOpen = signal(false);
  selectedPlace: string = "";
  selectedState: string = "";
  selectedEditId: string = "";

  openEditModal(place:string, state:string, id: string) {
    this.closeDeleteModal(); 

    this.selectedPlace = place;
    this.selectedState = state;
    this.selectedEditId = id;    
    this.isEditModalOpen.update(state => !state);
  }

  closeEditModal() {
    this.selectedPlace = "";
    this.selectedState = "";
    this.selectedEditId = "";
    this.isEditModalOpen.set(false);
  }

  // Delete Modal config
  isDeleteModalOpen = signal(false);
  selectedDeleteId: string = "";

  openDeleteModal(id: string) {
    this.closeEditModal();
    this.selectedDeleteId = id;
    this.isDeleteModalOpen.update(state => !state);
  }

  closeDeleteModal() {
    this.selectedDeleteId = "";
    this.isDeleteModalOpen.set(false);
  }

  


}
