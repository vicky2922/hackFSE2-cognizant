import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestServiceService {

  constructor(private http:HttpClient) { }

  public getAllCompany(){
    return this.http.get("http://localhost:8989/company/getall");
  }

  public getStockData(companyCode:string, startDate:string, endDate:string){
    return this.http.get("http://localhost:8989/stock/get/"+companyCode+"/"+startDate+"/"+endDate);
  }

}
