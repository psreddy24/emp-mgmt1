import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../person';
import {EmployeeRequest} from '../employeeRequest';
import {Observable} from 'rxjs';
import {PersonService} from '../person.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html'
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  employeeRequest: EmployeeRequest = new EmployeeRequest();
  submitted = false;
  person: Person = new Person();
  persons: Observable<Person[]>;

  constructor(private employeeService: EmployeeService,
              private personService: PersonService,
              private router: Router) { }

  ngOnInit() {
    this.personService.getPersonList()
      .subscribe(data => {
        console.log('31 Data ' + data);
        this.persons = data;
      }, error => console.log(error));
  }

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
    this.person = new Person();

  }

  save() {
    console.log('employeeRequest... ' + this.employeeRequest);
    this.employeeService.createEmployee(this.employeeRequest)
      .subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['']).then(() => {
      window.location.reload();
    });
  }
}
