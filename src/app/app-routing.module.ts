import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PrincipalComponent } from './principal/principal.component';
import { ProdutoComponent } from './produto/produto.component';
import { GerenciaComponent } from './gerencia/gerencia.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'produto/:id', component: ProdutoComponent },
  { path: 'gerencia', component: GerenciaComponent },
  { path: '', component: PrincipalComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
