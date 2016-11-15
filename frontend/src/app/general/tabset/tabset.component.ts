import { Component, ContentChildren, QueryList, AfterContentInit } from '@angular/core';

import { TabComponent } from './tab.component';

@Component({
    selector: 'educama-tabset',
    template:
    `<ul class="nav nav-tabs">
            <li *ngFor="let tab of tabset" (click)="selectTab(tab)" [class.active]="tab.active">
                <a class="clickable">{{tab.title}}</a>
            </li>
        </ul>
        <ng-content></ng-content>`
})
export class TabsetComponent implements AfterContentInit {

    // queries all children tabs
    @ContentChildren(TabComponent)
    tabset: QueryList<TabComponent>;

    // function is called when content is initialized (i.e. all children tabs has been queried)
    ngAfterContentInit() {
        // get all active tabs
        let activeTabs = this.tabset.filter((tab) => tab.active);
        // is no active tab -> activate first tab
        if (activeTabs.length === 0) {
            this.selectTab(this.tabset.first);
        }
    }

    selectTab(tab: TabComponent) {
        // deactivate all tabs
        this.tabset.toArray().forEach(tab => tab.active = false);
        // activate clicked tab
        tab.active = true;
    }
}
