export const HTML_TEMPLATE = `
    <div>
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <a class="navbar-brand">Übersicht</a>
            </div>
            <form class="navbar-form pull-right">
                <button id="addCase" class="btn btn-primary" (click)="createCaseInstance()">Neuen Transportauftrag anlegen</button>
            </form>
        </nav>
        <create-instance-modal (modalOutput)="getCaseData($event)"></create-instance-modal>
    </div>
`;