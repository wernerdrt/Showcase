import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CasesComponent } from './cases.component';
import { CaseDetailComponent } from './cases_detail.component';

const casesRoutes: Routes = [
  { path: 'cases', component: CasesComponent },
  { path: 'case/:id', component: CaseDetailComponent }
];

export const casesRouting: ModuleWithProviders = RouterModule.forChild(casesRoutes);
