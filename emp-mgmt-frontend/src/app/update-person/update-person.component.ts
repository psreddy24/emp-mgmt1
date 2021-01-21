import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../person.service';
import { Person } from '../person';

@Component({
  selector: 'app-update-person',
  templateUrl: './update-person.component.html'
})
export class UpdatePersonComponent implements OnInit {

  id: number;
  person: Person;
  existingPerson: Person;
  message: string;

  constructor(private route: ActivatedRoute, private router: Router,
              private personService: PersonService) { }

  ngOnInit() {
    this.person = new Person();

    this.id = this.route.snapshot.params.id;

    this.personService.getPerson(this.id)
      .subscribe(data => {
        console.log('31 Data ' + data);
        this.person = data;
      }, error => console.log(error));
  }

  updatePerson() {

    this.personService.getPerson(this.id)
      .subscribe(data => {
        console.log('31 Data ' + data);
        this.existingPerson = data;
        this.existingPerson.firstName = this.person.firstName;
        this.existingPerson.lastName = this.person.lastName;
        this.existingPerson.birthDate = this.person.birthDate;
        this.personService.updatePerson(this.id, this.existingPerson)
          // tslint:disable-next-line:no-shadowed-variable
          .subscribe(data => console.log(data), error => console.log(error));
        // this.person = new Person();
        // this.gotoList();
      }, error => console.log(error));
    this.message = 'Record has been updated Successfully';
  }

  onSubmit() {
    this.updatePerson();
  }

  gotoList() {
    this.router.navigate(['/persons']);
  }
}
