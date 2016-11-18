import {Component, Input} from "@angular/core";
import {Router} from "@angular/router";
import {HTML_TEMPLATE} from "./control_detail.component.html";

@Component({
    selector: 'control-panel-detail',
    template: HTML_TEMPLATE
})
export class DetailControlComponent {

    @Input()
    id: number;

    constructor(private router: Router) {
    }

    back() {
        this.router.navigate(['/cases']);
    }
}
