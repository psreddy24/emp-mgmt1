import {EmployeeService} from './employee.service';
import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import any = jasmine.any;

describe('EmployeeService', () => {
  let service: EmployeeService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmployeeService]
    }).compileComponents();
    service = TestBed.inject(EmployeeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('#test: service.getEmployee', () => {
    service.getEmployee(12).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Employees/12`);
    expect(request.request.method).toBe('GET');
  });

  it('#test: service.createEmployee', () => {
    const anObject = {};
    service.createEmployee(anObject).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Employees`);
    expect(request.request.method).toBe('POST');
  });

  it('#test: service.updateEmployee', () => {
    const anObject = {};
    service.updateEmployee(1, anObject).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Employees/1`);
    expect(request.request.method).toBe('PUT');
  });

  it('#test: service.deleteEmployee', () => {
    service.deleteEmployee('1').subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Employees/1`);
    expect(request.request.method).toBe('DELETE');
  });

  it('#test: service.getEmployeeList', () => {
    service.getEmployeeList().subscribe(data => {
      expect(data).toBeTruthy();
    });
    const request = httpMock.expectOne(`http://localhost:9092/api/Employees`);
    expect(request.request.method).toBe('GET');
  });


  afterEach(() => {
    httpMock.verify();
  });
});
