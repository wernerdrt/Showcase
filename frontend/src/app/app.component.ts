import {Component} from "@angular/core";
import {HTML_TEMPLATE} from "./app.component.html";

@Component({
    selector: 'educama-app',
    template: HTML_TEMPLATE,
    styles: [`.active-nav { color: white !important; }
              .nonclickable:hover { color: #9d9d9d !important }`],
})
export class AppComponent {
}
