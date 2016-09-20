import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Case } from './data/case';
import { CasesService } from './data/cases.service';

@Component({
  selector: 'my-case-detail',
  templateUrl: 'templates/cases/cases_detail.component.html',
  styles: ['th { width: 10%; border: none !important; } td { border: none !important; }']
})
export class CaseDetailComponent implements OnInit {
  instance: Case;

  private sub: any;

  constructor(private casesService: CasesService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      let id = +params['id']; // (+) converts string 'id' to a number
      this.instance = this.casesService.getCase(id);
    });
    // use following approach if component will never be reused with other params
    //let id = +this.route.snapshot.params['id'];
    //this.instance = this.casesService.getCase(id);
    // in this case: ngOnDestroy() can be deleted (no unsubscribing needed) 
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
