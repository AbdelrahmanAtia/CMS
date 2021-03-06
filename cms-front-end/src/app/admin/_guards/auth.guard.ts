import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../_services/authentication.service';

export class AuthGuard implements CanActivate { 

    constructor(private authenticationService: AuthenticationService,
                private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const authToken: string = this.authenticationService.getAuthToken();                
        
        if (authToken) {
            //authorized, so return true
            return true;
        }

        //not logged in so redirect to login page with the return url
        this.router.navigate(['login']);
        return false;
    }
}