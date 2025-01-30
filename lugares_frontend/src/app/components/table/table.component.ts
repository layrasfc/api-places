import { Component, EventEmitter, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditModalComponent } from '../edit-modal/edit-modal.component';
import { DeleteModalComponent } from '../delete-modal/delete-modal.component';
import { HttpClient } from '@angular/common/http';
import { format } from 'date-fns';

@Component({
  selector: 'app-table',
  imports: [CommonModule, EditModalComponent, DeleteModalComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  items: any[] = []; // Array de itens

  constructor(private http: HttpClient) { } // Injetar HttpClient

  ngOnInit(): void {
    this.loadData(); // Carregar dados ao inicializar o componente
  }

  loadData() {
    this.http.get<any[]>('http://localhost:8080/places') // Fazer a requisição para o backend
      .subscribe({
        next: (data) => {
          console.log(data);  // Verifica os dados no console
          
          this.items = data.map(item => {
            item.createdAt = format(new Date(item.createdAt), 'dd/MM/yyyy');

            item.updatedAt = format(new Date(item.updatedAt), 'dd/MM/yyyy - HH:mm');
            
            return item;
          });
        },
        error: (err) => {
          console.error('Erro ao carregar dados:', err); // Lidar com erros
        }
      });
    }

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
