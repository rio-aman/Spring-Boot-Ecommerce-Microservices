export const authConfig = {
  clientId: 'oauth2-pkce-demo',
  authorizationEndpoint: 'http://127.0.0.1:8443/realms/oauth2-demo/protocol/openid-connect/auth',
  tokenEndpoint: 'http://127.0.0.1:8443/realms/oauth2-demo/protocol/openid-connect/token', 
  redirectUri: 'http://localhost:5173',
  scope: 'openid profile email offline_access',
  onRefreshTokenExpire: (event) => event.logIn(),
}