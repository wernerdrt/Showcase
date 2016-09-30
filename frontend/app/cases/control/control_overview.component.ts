import { Component, ViewChild, Output, EventEmitter } from '@angular/core';

import { CasesService } from '../data/cases.service';
import { CreateInstanceModalComponent } from '../modal/create_instance_modal.component';

@Component({
    selector: 'control-panel-overview',
    templateUrl: 'templates/cases/control/control_overview.component.html'
})
export class OverviewControlComponent {

    @ViewChild(CreateInstanceModalComponent) modal: CreateInstanceModalComponent;

    @Output() loadCases: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor(
        private casesService: CasesService) { }

    createCaseInstance() {
        this.modal.showModal();
    }

    getCaseData(data) {
        if (data !== null) {
            this.casesService.addCase(data.type, data.customer);
            this.loadCases.emit(true);
        }
    }

    reloadCases(shouldLoadCases: boolean) {
        this.loadCases.emit(shouldLoadCases);
    }
}
