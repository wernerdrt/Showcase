export const HTML_TEMPLATE = `
    <div class="navbar navbar-default" role="navigation">
        <h1 class="navbar-form pull-left" style="display: inline;">Auftrag {{ id }}</h1>
        <form class="navbar-form pull-right">
            <button class="btn btn-primary" (click)="back()">Zurück zur Übersicht</button>
        </form>
    </div>
`;