import { Component, OnInit } from '@angular/core';
import { Employee} from '../employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import {Person} from '../person';


@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html'
})
export class UpdateEmployeeComponent implements OnInit {

  id: number;
  employee: Employee;
  existingEmployee: Employee;
  person: Person;
  message: string;


  constructor(private route: ActivatedRoute, private router: Router,
              private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();
    this.existingEmployee = new Employee();
    this.person = new Person();
    this.message = '';
    this.id = this.route.snapshot.params.id;

    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
        console.log('31 Data ' + data);
        this.employee = data;
      }, error => console.log(error));
  }

  updateEmployee() {
    this.employeeService.getEmployee(this.id).subscribe((data) => {
      console.log('40 Data ' + data);
      this.existingEmployee = data;
      // Update Data
      this.existingEmployee.employedDate =  this.employee.employedDate;
      this.existingEmployee.terminatedDate =  this.employee.terminatedDate;
      console.log(' existingEmployee ' + this.existingEmployee);

      this.employeeService.updateEmployee(this.id, this.existingEmployee)
        // tslint:disable-next-line:no-shadowed-variable
        .subscribe(data => {
          console.log(data);
          this.employee = new Employee();
          // this.gotoList();
        }, error => console.log(error));

    });
    this.message = 'Record has been updated Successfully';
  }

  onSubmit() {
    this.updateEmployee();
    // this.gotoList();
  }

  gotoList() {
    this.router.navigate(['']);
  }

  gotoUpdate() {
    console.log(' id = '  + this.id );
    this.router.navigate(['update/${this.id}']);
  }
}
