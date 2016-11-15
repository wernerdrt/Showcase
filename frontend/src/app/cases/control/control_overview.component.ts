import {Component, ViewChild, Output, EventEmitter} from "@angular/core";
import {CasesService} from "../data/cases.service";
import {CreateInstanceModalComponent} from "../modal/create_instance_modal.component";
import {HTML_TEMPLATE} from "./control_overview.component.html";

@Component({
    selector: 'control-panel-overview',
    template: HTML_TEMPLATE
})
export class OverviewControlComponent {

    @ViewChild(CreateInstanceModalComponent) modal: CreateInstanceModalComponent;

    @Output() loadCases: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor(private casesService: CasesService) {
    }

    createCaseInstance() {
        this.modal.showModal();
    }

    getCaseData(data: any) {
        if (data !== null) {
            this.casesService.addCase(data.type, data.customer);
            this.loadCases.emit(true);
        }
    }

    reloadCases(shouldLoadCases: boolean) {
        this.loadCases.emit(shouldLoadCases);
    }
}
