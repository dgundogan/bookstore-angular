import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    private loggedIn = false;

    constructor(private loginService: LoginService,
                private router: Router){ }
    
    ngOnInit() {
       this.loginService.chechSession().subscribe(
                res => {
                    this.loggedIn = true;
                },
                err=>{
                    this.loggedIn = false;
                }  
         );
    }

    logout(){
        this.loginService.logout().subscribe(
            res=>{
                location.reload();
            },
            err=>{
                console.log(err);
            }
        );

        this.router.navigate(['/']);
    }

}
