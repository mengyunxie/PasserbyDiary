/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {
    SIDE_MENU,
    NAVIGATION,
    DEFAULT_LABEL_KEY,
    formatDate,
} from './constants';

function MyDiaryItem({diary, onSetNavigation, onSetCurrentDiary}) {

    return (
        <div 
            className="diary-item"
            onClick={ (e) => {
                e.preventDefault();
                onSetCurrentDiary(diary);
                onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.MYDIARY].DETAIL});
            }}
        >
            <p className="diary-item-date">{formatDate(diary.date)}</p>
            <p className="diary-item-intro">{diary.intro}</p>
            {diary.label.key !== DEFAULT_LABEL_KEY && <div 
                    className={`diary-item-label label-item-color ${diary.label.color}`} 
                >
                    <i className="gg-tag"></i>
                    <span className='diary-item-label-title'>{diary.label.key}</span>
                </div>
            }  
        </div>
    );
}
export default MyDiaryItem;