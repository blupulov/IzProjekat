import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PointFourComponent } from './components/point-four/point-four.component';
import { PointOneComponent } from './components/point-one/point-one.component';
import { PointThreeComponent } from './components/point-three/point-three.component';
import { PointTwoComponent } from './components/point-two/point-two.component';

const routes: Routes = [
  {path:'', redirectTo:'home', pathMatch:'full'},
  {path:'home', component: HomeComponent},
  
  {path:'one', component: PointOneComponent},
  {path:'two', component: PointTwoComponent},
  {path:'three', component: PointThreeComponent},
  {path:'four', component: PointFourComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
