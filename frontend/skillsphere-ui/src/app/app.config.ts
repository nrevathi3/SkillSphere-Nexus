import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners
} from '@angular/core';

import { provideRouter } from '@angular/router';

import {
  provideHttpClient,
  withInterceptors
} from '@angular/common/http';

import {
  provideKeycloak,
  createInterceptorCondition,
  IncludeBearerTokenCondition,
  includeBearerTokenInterceptor,
  INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG
} from 'keycloak-angular';

import { routes } from './app.routes';

const urlCondition =
  createInterceptorCondition<IncludeBearerTokenCondition>({
    urlPattern: /^http:\/\/localhost:8090(\/.*)?$/i,
    bearerPrefix: 'Bearer'
  });

export const appConfig: ApplicationConfig = {

  providers: [

    provideBrowserGlobalErrorListeners(),

    provideKeycloak({
      config: {
        url: 'http://localhost:8081',
        realm: 'skillsphere',
        clientId: 'skillspherefrontend'
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false
      }
    }),

    {
      provide: INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
      useValue: [urlCondition]
    },

    provideRouter(routes),

    provideHttpClient(
      withInterceptors([
        includeBearerTokenInterceptor
      ])
    )

  ]
};
