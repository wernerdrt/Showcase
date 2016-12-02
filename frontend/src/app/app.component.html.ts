export const HTML_TEMPLATE = `
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <div class="navbar-brand nonclickable">Educama</div>
        </div>
        <ul class="nav navbar-nav">
          <li><a routerLink="/" routerLinkActive="active">Home</a></li>
          <li><a id="casePage" routerLink="/cases" routerLinkActive="active">Cases</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <!-- Insert the Login / Logout here -->
        </ul>
      </div>
    </nav>
    <router-outlet></router-outlet>
`;