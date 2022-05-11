import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchModuleComponent } from './search-module/search-module.component';
import { DisplayModuleComponent } from './display-module/display-module.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchModuleComponent,
    DisplayModuleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
