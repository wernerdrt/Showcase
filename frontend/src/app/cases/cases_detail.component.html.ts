export const HTML_TEMPLATE = `
    <control-panel-detail [id]="instance.id"></control-panel-detail>
    <table class="table">
        <tr>
            <th>Transportart:</th>
            <td>{{ instance.type }}</td>
        </tr>
        <tr>
            <th>Sendungsnummer:</th>
            <td>{{ instance.id }}</td>
        </tr>
        <tr>
            <th>Kunde:</th>
            <td>{{ instance.customer }}</td>
        </tr>
    </table>
`;