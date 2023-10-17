/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {
    SIDE_MENU,
    NAVIGATION,
    formatDate,
  } from './constants';
import Avatar from './Avatar';
function PasserbyDetail({
    previousNavigation, 
    currentDiary,
    onSetNavigation,
    onGetMyPasserbyDiaries,
    onGetPasserbyDiaries,
}) {
    return (
        <div className='passerby-details'>
            <div className='passerby-details-tools'>
                <button 
                    type="button"
                    className='go-back'
                    onClick={ (e) => {
                        e.preventDefault();
                        onSetNavigation({currentNavigation: previousNavigation});
                        if(previousNavigation === NAVIGATION[SIDE_MENU.PASSERBY].DEFAULT) {
                            onGetPasserbyDiaries();
                        }
                        if(previousNavigation === NAVIGATION[SIDE_MENU.PASSERBY].MINE) {
                            onGetMyPasserbyDiaries();
                        }
                    }}
                >
                    <i className="gg-arrow-left"></i>
                    <span className='go-back-title'>Go Back</span>
                </button>
            </div>
            <Avatar avatar={currentDiary.avatar} username={currentDiary.username} />
            <div className="passerby-details-date">{formatDate(currentDiary.date)}</div>
            <div className='passerby-details-content'>{currentDiary.details}</div>
        </div>
    );
}
export default PasserbyDetail;