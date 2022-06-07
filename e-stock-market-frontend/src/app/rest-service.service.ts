import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestServiceService {

  ROOT_URL : string = "http://hackfse2apigateway-env.eba-mpsgx5wq.us-west-2.elasticbeanstalk.com/";

  constructor(private http:HttpClient) { }

  public getAllCompany(){
    return this.http.get(this.ROOT_URL+"company/getall");
  }

  public getStockData(companyCode:string, startDate:string, endDate:string){
    //return this.http.get("http://localhost:8989/stock/get/"+companyCode+"/"+startDate+"/"+endDate);
    return this.http.get(this.ROOT_URL+"stock/getWithDetail/"+companyCode+"/"+startDate+"/"+endDate);
  }

  public getCompanyData(companyCode:string){
    return this.http.get(this.ROOT_URL+"company/info/"+companyCode);
  }

}
