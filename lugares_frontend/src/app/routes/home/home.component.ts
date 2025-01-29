import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { TableComponent } from '../../components/table/table.component';
import { BaseLayoutComponent } from '../../shared/base-layout/base-layout.component';
import { SearchComponent } from '../../components/search/search.component';
import { PagesComponent } from '../../components/pages/pages.component';
import { LineComponent } from '../../components/line/line.component';

@Component({
  selector: 'app-home',
  imports: [
    BaseLayoutComponent,
    TableComponent,
    SearchComponent,
    PagesComponent,
    LineComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  @ViewChild(TableComponent) tableComponent!: TableComponent;

  refreshTable() {
    if (this.tableComponent) {
      this.tableComponent.loadData(); // Chama o método de recarga da tabela
    }
  }
  
}
