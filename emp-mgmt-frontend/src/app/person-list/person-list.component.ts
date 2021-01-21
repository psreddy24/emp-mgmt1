import { Observable } from 'rxjs';
import { PersonService } from '../person.service';
import { Person } from '../person';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html'
})
export class PersonListComponent implements OnInit {
  persons: Observable<Person[]>;
  message: string;

  constructor(private personService: PersonService,
              private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.personService.getPersonList().subscribe(
      data => {
        console.log('Person List ' + data);
        this.persons = data;
      },
      error => console.log(error));
  }


  deletePerson(id: number) {
    this.personService.deletePerson(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
          this.message = 'Record has been deleted';
        },
        error =>  {
          console.log(error);
          this.message = 'Unable to delete record. Foreign key references';
        });
  }

  personDetails(id: number) {
    console.log('ID ==> ' + id);
    this.router.navigate(['persondetails', id]);
  }

  updatePerson(id: number) {
    this.router.navigate(['personupdate', id]);
  }
}
