/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {SIDE_MENU, DEFAULT_LABEL_KEY} from './constants';

function SideMenu({
    menu, 
    onSetMenu,
    onGetDiariesByLabel,
    onGetPasserbyDiaries,
}) {

    return (
        <div className="side-menu">
            <ul className="side-menu-contents">
                <li 
                    className={`side-menu-item ${menu === SIDE_MENU.PASSERBY ? "focus" : ""}`}
                    onClick={ (e) => {
                        e.preventDefault();
                        onSetMenu(SIDE_MENU.PASSERBY);
                        onGetPasserbyDiaries();
                    }}
                >
                    <span className="side-menu-icon"><i className="gg-data"></i></span>
                    <span>{SIDE_MENU.PASSERBY}</span>
                </li>
                <li
                    className={`side-menu-item ${menu === SIDE_MENU.MYDIARY ? "focus" : ""}`}
                    onClick={ (e) => {
                        e.preventDefault();
                        onSetMenu(SIDE_MENU.MYDIARY);
                        onGetDiariesByLabel(DEFAULT_LABEL_KEY);
                    }}
                >
                    <span className="side-menu-icon"><i className="gg-details-more"></i></span>
                    <span>{SIDE_MENU.MYDIARY}</span>
                </li>
                <li
                    className={`side-menu-item ${menu === SIDE_MENU.SETTING ? "focus" : ""}`}
                    onClick={ (e) => {
                        e.preventDefault();
                        onSetMenu(SIDE_MENU.SETTING);
                    }}
                >
                    <span className="side-menu-icon"><i className="gg-layout-grid-small"></i></span>
                    <span>{SIDE_MENU.SETTING}</span>
                </li>
            </ul>
            <p className='copyright'> © 2023 Passerby Diary</p>
        </div>
    );
}
export default SideMenu;