// import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html'
})
export class EmployeeListComponent implements OnInit {
  employees: Observable<Employee[]>;
  message: string;

  constructor(private employeeService: EmployeeService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    console.log(' EmployeeList = ' + this.employeeService.getEmployeeList());
    this.employees = this.employeeService.getEmployeeList();
  }

  deleteEmployee(id: string) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
          this.message = 'Record has been deleted';
        },
        error => console.log(error));
  }

  employeeDetails(id: string) {
    console.log('ID ==> ' + id);
    this.router.navigate(['details', id]);
  }

  updateEmployee(id: string) {
    this.router.navigate(['update', id]);
  }
}
