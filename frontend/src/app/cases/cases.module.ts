import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CasesComponent } from './cases.component';
import { CaseListComponent } from './cases_list.component';
import { CaseDetailComponent } from './cases_detail.component';
import { TasksComponent } from './cases_overview_tasks.component';
import { OverviewControlComponent } from './control/control_overview.component';
import { DetailControlComponent } from './control/control_detail.component';
import { CreateInstanceModalComponent } from './modal/create_instance_modal.component';

import { CasesService } from './data/cases.service';
import { casesRouting } from './cases.routing';

import { TabsetComponent } from '../general/tabset/tabset.component';
import { TabComponent } from '../general/tabset/tab.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        casesRouting
    ],
    declarations: [
        CasesComponent,
        CaseListComponent,
        CaseDetailComponent,
        TasksComponent,
        OverviewControlComponent,
        DetailControlComponent,
        CreateInstanceModalComponent,
        TabsetComponent,
        TabComponent
    ],
    providers: [
        CasesService
    ]
})
export class CasesModule { }
