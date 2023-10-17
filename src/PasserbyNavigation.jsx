/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { useState } from 'react';
import { SIDE_MENU, NAVIGATION } from './constants';

function PasserbyNavigation({
    onSetNavigation, 
    onGetMyPasserbyDiaries,
    onGetPasserbyDiaries,
}) {
    const [navigation, setNavigation] = useState(NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT);

    return (
        <ul className="navigation-contents">
            <li 
                className={`navigation-item ${navigation === NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT ? "focus" : ""}`}
                onClick={ (e) => {
                    e.preventDefault();
                    setNavigation(NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT);
                    onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT});
                    onGetPasserbyDiaries();
                }}
            >
                {NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT}
            </li>
            <li 
                className={`navigation-item ${navigation === NAVIGATION[SIDE_MENU.PASSERBY].MINE ? "focus" : ""}`}
                onClick={ (e) => {
                    e.preventDefault();
                    setNavigation(NAVIGATION[SIDE_MENU.PASSERBY].MINE);
                    onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.PASSERBY].MINE});
                    onGetMyPasserbyDiaries();
                }}
            >
                {NAVIGATION[SIDE_MENU.PASSERBY].MINE}
            </li>
        </ul>
    );
}
export default PasserbyNavigation;