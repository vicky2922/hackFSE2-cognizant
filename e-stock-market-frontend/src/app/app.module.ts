import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http'
import { SearchModuleComponent } from './search-module/search-module.component';
import { DisplayModuleComponent } from './display-module/display-module.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { RestServiceService } from './rest-service.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchModuleComponent,
    DisplayModuleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,  
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [RestServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
