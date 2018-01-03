import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";
import {TaskListPageComponent} from "./container/task-list-page.component";
import {TaskListComponent} from "./presentationals/task-list.component";
import {CaseUITaskListComponent} from "./caseUI-presentationals/caseUI-task-list.component";

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: [
    TaskListPageComponent,
    TaskListComponent,
    CaseUITaskListComponent
  ],
  exports: [
    TaskListPageComponent
  ]
})
export class TaskListModule {
}
