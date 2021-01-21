
import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PersonService } from '../person.service';
import { Person } from '../person';

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html'
})
export class PersonDetailsComponent implements OnInit {

  id: number;
  person: Person;

  constructor(private route: ActivatedRoute, private router: Router,
              private personService: PersonService) { }

  ngOnInit() {
    this.person = new Person();

    this.id = this.route.snapshot.params.id;

    this.personService.getPerson(this.id)
      .subscribe(data => {
        console.log(data);
        this.person = data;
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['persons']);
  }
}
