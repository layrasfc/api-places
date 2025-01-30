import { Component, EventEmitter, Output, signal, ViewChild } from '@angular/core';
import { TableComponent } from '../../components/table/table.component';
import { BaseLayoutComponent } from '../../shared/base-layout/base-layout.component';
import { SearchComponent } from '../../components/search/search.component';
import { LineComponent } from '../../components/line/line.component';
import { HttpClientModule } from '@angular/common/http';
import { AddModalComponent } from '../../components/add-modal/add-modal.component';

@Component({
  selector: 'app-home',
  imports: [
    BaseLayoutComponent,
    TableComponent,
    SearchComponent,
    LineComponent,
    HttpClientModule,
    AddModalComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  isAddModalOpen = signal(false);
  
  openAddModal() {

      this.isAddModalOpen.update(state => !state);
    }
  
    closeAddModal() {
      this.isAddModalOpen.set(false);
    }
  
}
