import {Component, OnInit} from "@angular/core";
import {Case} from "./data/case";
import {CasesService} from "./data/cases.service";
import {HTML_TEMPLATE} from "./cases.component.html";

@Component({
    selector: 'my-cases',
    template: HTML_TEMPLATE,
    styles: ['th { border-bottom: 3px solid #ddd; border-top: 3px solid #ddd !important; }']
})
export class CasesComponent implements OnInit {
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
