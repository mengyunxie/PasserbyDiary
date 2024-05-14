/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { useState } from 'react';
import { SIDE_MENU, NAVIGATION } from './constants';

function SettingNavigation({onSetNavigation}) {
    const [navigation, setNavigation] = useState(NAVIGATION[SIDE_MENU.SETTING].DEFAULT);
    
    return (
        <ul className="navigation-contents">
            <li 
                className={`navigation-item ${navigation === NAVIGATION[SIDE_MENU.SETTING].DEFAULT ? "focus" : ""}`}
                onClick={ (e) => {
                    e.preventDefault();
                    setNavigation(NAVIGATION[SIDE_MENU.SETTING].DEFAULT);
                    onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.SETTING].DEFAULT});
                }}
            >
                {NAVIGATION[SIDE_MENU.SETTING].DEFAULT}
            </li>
            <li 
                className={`navigation-item ${navigation === NAVIGATION[SIDE_MENU.SETTING].ABOUT ? "focus" : ""}`}
                onClick={ (e) => {
                    e.preventDefault();
                    setNavigation(NAVIGATION[SIDE_MENU.SETTING].ABOUT);
                    onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.SETTING].ABOUT});
                }}
            >
                {NAVIGATION[SIDE_MENU.SETTING].ABOUT}
            </li>
        </ul>
    );
}
export default SettingNavigation;