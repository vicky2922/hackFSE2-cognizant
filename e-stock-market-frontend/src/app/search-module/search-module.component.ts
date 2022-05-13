import { Component, OnInit, Output, EventEmitter} from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';  
import { RestServiceService } from '../rest-service.service';

@Component({
  selector: 'app-search-module',
  templateUrl: './search-module.component.html',
  styleUrls: ['./search-module.component.css']
})
export class SearchModuleComponent implements OnInit {

  companyList:any;
  message: string = "Hey I am from search module !";
  StartDate:Date | undefined;
  EndDate:Date | undefined;
  form = new FormGroup({  
  }); 
  @Output() messageEvent = new EventEmitter<string>();

 constructor(private formBuilder: FormBuilder, private restService: RestServiceService) { } 

 sendMessage() {
  this.messageEvent.emit(this.message)
}

  ngOnInit(): void {
    this.fetchAllCompany();
    console.log(this.companyList);
    this.form = this.formBuilder.group({
      StartDate : '',
      EndDate :'',
    company: new FormControl('', Validators.required)
    });
  }
    
  get f(){  
    return this.form.controls;  
  }
    
  submit(){  
    var json = JSON.stringify(this.form.value);
    console.log(json);
    this.messageEvent.emit(json);
   // this.fetchStaticStockData("ADGRE","10052022","12052022");
  }  

  fetchAllCompany(){
    let response = this.restService.getAllCompany();
    response.subscribe((data)=>this.companyList=data);
  }

}
