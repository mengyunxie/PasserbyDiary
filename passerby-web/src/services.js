/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * Description: Offer fetch() calls to communicate with the server.
 * This code is a part of the final project of the INFO 6250 course.
 */

import {baseUrl} from './constants';

/* Login */
export function fetchLogin(username) {

  return fetch(baseUrl + '/api/v1/session', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'content-type': 'application/json', // set this header when sending JSON in the body of request
    },
    body: JSON.stringify( { username } ),
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

/* Logout */
export function fetchLogout() {
  return fetch(baseUrl + '/api/v1/session', {
    method: 'DELETE',
    credentials: 'include',
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

/* Check for an existing session */
export function fetchSession() {
  return fetch(baseUrl + '/api/v1/session', {
    method: 'GET',
    credentials: 'include',
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Update user's avatar
export function fetchUpdateUserAvatar(avatar) {
  return fetch(baseUrl + '/api/v1/session', {
    method: 'PATCH',
    credentials: 'include',
    headers: new Headers({
      'content-type': 'application/json',
    }),
    body: JSON.stringify({avatar}),
  })
  .catch( () => Promise.reject({ error: 'networkError' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Add a new diary
export function fetchAddDiary({details, labelKey, published}) {
  return fetch(baseUrl +'/api/v1/diaries', {
    method: 'POST',
    credentials: 'include',
    headers: new Headers({
      'content-type': 'application/json',
    }),
    body: JSON.stringify({details, labelKey, published}),
  })
  .catch( () => Promise.reject({ error: 'networkError' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Update user's diary
export function fetchUpdateDiary({id, details, labelKey, published}) {
  return fetch(baseUrl +`/api/v1/diaries/${id}`, {
    method: 'PATCH',
    credentials: 'include',
    headers: new Headers({
      'content-type': 'application/json',
    }),
    body: JSON.stringify({details, labelKey, published}),
  })
  .catch( () => Promise.reject({ error: 'networkError' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Delete user's diary
export function fetchDeleteDiary(id) {
  return fetch(baseUrl +`/api/v1/diaries/${id}`, {
    method: 'DELETE',
    credentials: 'include',
  })
  .catch( () => Promise.reject({ error: 'networkError' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Get a user's diaries of different labels
export function fetchDiariesByLabel(label) {
  return fetch(baseUrl +`/api/v1/diaries/label/${label}`, {
    method: 'GET',
    credentials: 'include',
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Get passersby's diaries
export function fetchPasserbyDiaries() {
  return fetch(baseUrl +'/api/v1/diaries', {
    method: 'GET',
    credentials: 'include',
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}

// Get a user's passersby's diaries
export function fetchMyPasserbyDiaries() {
  return fetch(baseUrl +'/api/v1/diaries/username/mine', {
    method: 'GET',
    credentials: 'include',
  })
  .catch( err => Promise.reject({ error: 'network-error' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}