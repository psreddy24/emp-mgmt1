import { PersonService } from '../person.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person} from '../person';
import {PersonRequest} from '../personRequest';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html'
})
export class CreatePersonComponent implements OnInit {

  submitted = false;
  person: Person = new Person();
  personRequest: PersonRequest = new PersonRequest();

  constructor(private personService: PersonService,
              private router: Router) { }

  ngOnInit() {
  }

  newPerson(): void {
    this.submitted = false;
    this.person = new Person();

  }

  save() {
    console.log('Posting... ' + this.personRequest);
    this.personService.createPerson(this.personRequest)
      .subscribe(data => console.log(data), error => console.log(error));
    this.person = new Person();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['persons']).then(() => {
      window.location.reload();
    });
  }
}
