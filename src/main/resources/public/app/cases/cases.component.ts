import { Component } from '@angular/core';

import { Case } from './data/case';
import { CasesService } from './data/cases.service';

@Component({
    selector: 'my-cases',
    templateUrl: 'templates/cases/cases.component.html',
    styles: ['th { border-bottom: 3px solid #ddd; border-top: 3px solid #ddd !important; }']
})
export class CasesComponent {
    cases: Case[] = [];

    constructor(private casesService: CasesService) {
    }

    ngOnInit() {
        this.casesService.loadCases();
        this.getCases();
    }

    getCases() {
        this.casesService.getCases().then(cases => this.cases = cases);
    }

    loadCases(shouldReload: boolean) {
        if (shouldReload) {
            this.getCases();
        }
    }
}
