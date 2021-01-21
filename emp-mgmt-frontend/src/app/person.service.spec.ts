import {EmployeeService} from './employee.service';
import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import any = jasmine.any;
import {PersonService} from './person.service';

describe('PersonService', () => {
  let service: PersonService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmployeeService]
    }).compileComponents();
    service = TestBed.inject(PersonService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('#test: service.getPerson', () => {
    service.getPerson(12).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Person/12`);
    expect(request.request.method).toBe('GET');
  });

  it('#test: service.createEmployee', () => {
    const anObject = {};
    service.createPerson(anObject).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Person`);
    expect(request.request.method).toBe('POST');
  });

  it('#test: service.updatePerson', () => {
    const anObject = {};
    service.updatePerson(1, anObject).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Person/1`);
    expect(request.request.method).toBe('PUT');
  });

  it('#test: service.deletePerson', () => {
    service.deletePerson(1).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Person/1`);
    expect(request.request.method).toBe('DELETE');
  });

  it('#test: service.getPersonList', () => {
    service.getPersonList().subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Person`);
    expect(request.request.method).toBe('GET');
  });


  afterEach(() => {
    httpMock.verify();
  });
});
