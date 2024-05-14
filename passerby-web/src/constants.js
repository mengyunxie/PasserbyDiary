/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

// The system's build-in side menus
export const SIDE_MENU = {
  PASSERBY: 'Passerby',
  MYDIARY: 'MyDiary',
  SETTING: 'Setting',
};

// The system's build-in navigations
export const NAVIGATION = {
  [SIDE_MENU.PASSERBY]: {
    DEFAULT: 'Passerby Diaries',
    MINE: 'My Passerby Diaries',
    DETAIL: 'PasserbyDetail'
  },
  [SIDE_MENU.MYDIARY]: {
    DEFAULT: 'MyDiaryList',
    ADD: 'MyDiaryAdd',
    DETAIL: 'MyDiaryDetail',
    EDIT: 'MyDiaryEdit',
  },
  [SIDE_MENU.SETTING]: {
    DEFAULT: 'Profile',
    ABOUT: 'About',
  },
};

// Define the default label's key 
export const DEFAULT_LABEL_KEY = 'all';

// Define keys of avatar for import avatars in avatar component
export const AVATARS_KEY = {
  DEFAULT: 'Default',
  GIRL: 'Girl',
  BOY: 'Boy',
  FEMALE: 'Female',
  MALE: 'Male',
  GRANDMA: 'Grandma',
  GRANDPA: 'Grandpa',
  CAT: 'Cat',
  BEAR: 'Bear',
  DEER: 'Deer',
  DOG: 'Dog',
  FOX: 'Fox',
  HAPPY: 'Happy',
  WONDER: 'Wonder',
  BATMAN: 'Batman',
};

// Define the actions for reducer
export const ACTIONS = {
  WAIT_LOG_IN: 'waitLogIn',
  LOG_IN: 'logIn',
  LOG_OUT: 'logOut',
  UPDATE_AVATAR: 'updateAvatar',
  START_LOADING_DATA: 'startLoadingData',
  REPORT_ERROR: 'reportError',
  CLEAR_ERROR: 'clearError',
  TOGGLE_MENU: 'toggleMenu',
  TOGGLE_NAVIGATION: 'toggleNavigation',
  GET_DIARY: 'getDiary',
  GET_DIARIES: 'getDiaries',
  GET_PASSERBYDIARIES: 'getPasserbyDiaries',
  ADD_DIARY: 'addDiary',
  UPDATE_DIARY: 'updateDiary',
  DELETE_DIARY: 'deleteDiary',
  VIEW_DIARY: 'ViewDiary',
};

export const LOGIN_STATUS = {
  PENDING: 'pending',
  NOT_LOGGED_IN: 'notLoggedIn',
  IS_LOGGED_IN: 'loggedIn',
};

export const SERVER = {
  AUTH_MISSING: 'auth-missing',
  AUTH_INSUFFICIENT: 'auth-insufficient',
  REQUIRED_USERNAME: 'required-username',
  INVALID_USERNAME: 'invalid-username',
  INVALID_DIARY_DETAILS : 'invalid-diary-details',
  REQUIRED_DIARY_DETAILS: 'required-diary-details',
  INVALID_AVATAR: 'invalid-avatar',
  NO_SUCH_DIARY_ID: 'noSuchDiaryId',
  NOT_MATCH_USER: 'notMatchUser',
  INVALID_LABEL: 'invalid-label',
};

export const CLIENT = {
  NETWORK_ERROR: 'network-error',
  NO_SESSION: 'noSession',
  UNKNOWN_ACTION: 'unknownAction',
};

export const MESSAGES = {
  [CLIENT.NETWORK_ERROR]: 'Trouble connecting to the network.  Please try again',
  [SERVER.AUTH_MISSING]: 'Session id is invalid',
  [SERVER.AUTH_INSUFFICIENT]: 'This is a denied user, please try again.',
  [SERVER.REQUIRED_USERNAME]: 'The username can not be empty, please try again.',
  [SERVER.INVALID_USERNAME]: 'Please enter a valid (letters and/or numbers) username',
  [SERVER.INVALID_DIARY_DETAILS]: 'Diary details cannot be blank and the maximum length is 3000 letters, please try again!',
  [SERVER.REQUIRED_DIARY_DETAILS]: 'The details of a diary is required, please try again!',
  [SERVER.INVALID_AVATAR]: 'This is an invalid avatar.',
  [SERVER.INVALID_LABEL]: 'This is an invalid label.',
  [SERVER.NO_SUCH_DIARY_ID]: 'This diary is not exist.',
  [SERVER.NOT_MATCH_USER]: 'The diary does not match the user.',
  default: 'Something went wrong.  Please try again',
};

// Convert a timestamp to a readable time
export function formatDate(dateString) {
  const options = { 
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric'
  }
  return new Date(dateString).toLocaleDateString('en-US', options)
}