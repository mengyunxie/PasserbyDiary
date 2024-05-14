/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {
  LOGIN_STATUS,
  CLIENT,
  ACTIONS,
  AVATARS_KEY,
  SIDE_MENU,
  NAVIGATION,
} from './constants';

export const initialState = {
  error: '',
  username: '',
  avatar: AVATARS_KEY.DEFAULT, // Set default avatar
  loginStatus: LOGIN_STATUS.PENDING,
  isDashBoardPending: false, // DashBoard loading animation
  menu: SIDE_MENU.PASSERBY, // Set passerby as the default side menu  
  currentNavigation: NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT,
  previousNavigation: NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT, // Keep previous navigation for go back logic
  passerbyDiaries: [],
  diaries: [],
  currentDiary: {}, // For communication between components
  labels: [],
  avatars: []
};

function reducer(state, action) {
  switch(action.type) {

    case ACTIONS.WAIT_LOG_IN:
      return {
        ...state,
        loginStatus: LOGIN_STATUS.PENDING,
      };

    case ACTIONS.LOG_IN:
      return {
        ...state,
        error: '',
        loginStatus: LOGIN_STATUS.IS_LOGGED_IN,
        username: action.username,
        avatar: action.avatar,
        labels: action.labels,
        avatars: action.avatars,
      };

    case ACTIONS.LOG_OUT:
      return {
        ...state,
        username: '',
        avatar: AVATARS_KEY.DEFAULT,
        loginStatus: LOGIN_STATUS.NOT_LOGGED_IN,
        isDashBoardPending: false,
        menu: SIDE_MENU.PASSERBY,
        currentNavigation: NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT,
        previousNavigation: NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT,
        currentDiary: {},
        diaries: [],
        passerbyDiaries: [],
        labels: [],
        avatars: [],
      };
    case ACTIONS.UPDATE_AVATAR:
      return {
        ...state,
        isDashBoardPending: false,
        avatar: action.avatar,
      };

    case ACTIONS.START_LOADING_DATA:
      return {
        ...state,
        error: '',
        isDashBoardPending: true,
      };  

    case ACTIONS.REPORT_ERROR:
      return {
        ...state,
        isDashBoardPending: false,
        error: action.error || 'ERROR', // ERROR is just to ensure a truthy value
      };
    
    case ACTIONS.CLEAR_ERROR:
        return {
          ...state,
          error: '',
        };

    case ACTIONS.TOGGLE_MENU:
      return {
        ...state,
        menu: action.menu,
        currentDiary: {},
        error: '',
      };

    case ACTIONS.TOGGLE_NAVIGATION:
      return {
        ...state,
        currentNavigation: action.currentNavigation,
        previousNavigation: action.previousNavigation,
        error: '',
      };

    case ACTIONS.GET_DIARY:
      return {
        ...state,
        error: '',
        isDashBoardPending: false,
        currentDiary: action.diary
      };

    case ACTIONS.GET_DIARIES:
      return {
        ...state,
        error: '',
        isDashBoardPending: false,
        diaries: action.diaries
      };

    case ACTIONS.GET_PASSERBYDIARIES:
      return {
        ...state,
        error: '',
        isDashBoardPending: false,
        passerbyDiaries: action.passerbyDiaries
      };

    case ACTIONS.ADD_DIARY:
      // Add a new diary to the diary array
      const addDiaries = [...state.diaries];
      addDiaries.push(action.diary);
      return {
        ...state,
        isDashBoardPending: false,
        diaries: addDiaries,
        currentDiary: action.diary,
      };
    
    case ACTIONS.UPDATE_DIARY:
      // Update a diary from the diary array
      const updateDiaries = [...state.diaries];
      updateDiaries.forEach( (item, index) => {
        if(item.id === action.diary.id) {
          updateDiaries[index] = action.diary;
        } 
      });
      return {
        ...state,
        isDashBoardPending: false,
        diaries: updateDiaries,
        currentDiary: action.diary
      };

    case ACTIONS.DELETE_DIARY:
      // Delete a diary from the diary array
      const deleteDiaries = [...state.diaries];
      return {
        ...state,
        isDashBoardPending: false,
        diaries: deleteDiaries.filter(item => item.id !== action.id),
        currentDiary: {},
      };

    case ACTIONS.VIEW_DIARY:
        return {
          ...state,
          currentDiary: action.diary
        };

    default:
      throw new Error({ error: CLIENT.UNKNOWN_ACTION, detail: action }); // reporting detail for debugging aid, not shown to user
  }
}

export default reducer;
