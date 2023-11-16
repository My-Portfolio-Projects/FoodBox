import { inject } from '@angular/core'
import { CanActivateFn, Router } from '@angular/router';
import { AdminAuthService } from '../app/services/admin-auth.service';

export const adminAuthGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AdminAuthService);
  if (authService.isLoggedIn()) return true;
  else {
    router.navigate(['admin/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
};
