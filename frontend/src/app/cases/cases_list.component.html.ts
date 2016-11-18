export const HTML_TEMPLATE = `
    <table id="casesList" class="table">
        <tr>
            <th>Transportart</th>
            <th>Case ID / Sendungsnummer</th>
            <th>Kunde</th>
            <th>...</th>
            <th>...</th>
        </tr>
        <tr *ngFor="let case of cases" (click)="onSelect(case)" [class.highlight]="case === selectedCase">
            <td>{{ case.type }}</td>
            <td>{{ case.id }}</td>
            <td>{{ case.customer }}</td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <div *ngIf="selectedCase">
        <button class="btn btn-primary" (click)="gotoDetail()">View Details</button>
    </div>
`;