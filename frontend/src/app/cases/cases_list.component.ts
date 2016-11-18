import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Case } from './data/case';
import { CasesService } from './data/cases.service';

import {HTML_TEMPLATE} from './cases_list.component.html';

@Component({
  selector: 'cases-instances',
  template: HTML_TEMPLATE,
  styles: ['th { border-bottom: 3px solid #ddd; border-top: 3px solid #ddd !important; }']
})
export class CaseListComponent implements OnInit {
  cases: Case[];
  selectedCase: Case;

  constructor(
    private router: Router,
    private casesService: CasesService) { }

  getCases(): void {
    this.casesService.getCases().then(cases => this.cases = cases);
  }

  ngOnInit(): void {
    this.getCases();
  }

  onSelect(selectedCase: Case): void {
    this.selectedCase = selectedCase;
  }

  gotoDetail(): void {
    this.router.navigate(['/case', this.selectedCase.id]);
  }

}
