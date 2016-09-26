import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'control-panel-detail',
    templateUrl: 'templates/cases/control/control_detail.component.html',
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
