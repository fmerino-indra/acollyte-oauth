export const ACOLLYTE_API_BASE_URL = 'http://localhost:8080';
// export const ACOLLYTE_API_BASE_URL = 'http://localhost:8082/acollyte';

export const REMINDER_API_BASE_URL = 'http://localhost:8085';
export const DELIVERY_API_BASE_URL = 'http://localhost:8084';

export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://localhost:4200/oauth2/redirect';

export const GOOGLE_AUTH_URL = ACOLLYTE_API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const WSO2IS_AUTH_URL = ACOLLYTE_API_BASE_URL + '/oauth2/authorize/wso2is?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = ACOLLYTE_API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = ACOLLYTE_API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const HELLO_ACOLLYTE = ACOLLYTE_API_BASE_URL + '/acollyte/hello';
export const LIST_ACOLLYTE = ACOLLYTE_API_BASE_URL + '/acollyte/raffle';
export const MY_INFO = ACOLLYTE_API_BASE_URL + '/acollyte';
export const DETAIL_ACOLLYTE = ACOLLYTE_API_BASE_URL + '/acollyte/${userId}/raffle/${rpId}';
