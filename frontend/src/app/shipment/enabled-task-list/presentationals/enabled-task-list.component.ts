import {Component, Input, Output, EventEmitter} from "@angular/core";
import {EnabledTaskListRowModel} from "../container/enabled-task-list-page.model";
import {TaskResource} from "../../shipment-common/api/resources/task.resource";

@Component({
  selector: "educama-enabled-task-list",
  templateUrl: "./enabled-task-list.component.html"
})
export class EnabledTaskListComponent {

  @Input()
  public enabledTaskList: EnabledTaskListRowModel[];

  @Output()
  public selectedEnabledTask: TaskResource = new TaskResource();

  @Output()
  public taskSelectedEvent: EventEmitter<TaskResource> = new EventEmitter();

  public onRowSelect(event: TaskResource) {
    this.taskSelectedEvent.emit(event);
  }
}
