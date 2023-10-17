/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { 
    DEFAULT_LABEL_KEY, 
    SIDE_MENU,
    NAVIGATION,
    formatDate,
} from './constants';

function MyDiaryDetail({
    currentDiary,
    onSetNavigation,
    onDeleteDiary,
    onGetDiariesByLabel,
}) {
    return (
        <div className='mydiaries-details'>
            <div className='mydiaries-details-tools'>
                <button 
                    type="button"
                    className='go-back'
                    onClick={ (e) => {
                        e.preventDefault();
                        // Go to the diaries list page 
                        onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.MYDIARY].DEFAULT});
                        // Refresh the diaries list by 'DEFAULT_LABEL_KEY' label
                        onGetDiariesByLabel(DEFAULT_LABEL_KEY);
                    }}
                >
                    <i className="gg-arrow-left"></i>
                    <span className='go-back-title'>Go Back</span>
                </button>
                <button 
                    type="button"
                    className='to-delete'
                    onClick={ (e) => {
                        e.preventDefault();
                        onDeleteDiary(currentDiary.id);
                        onGetDiariesByLabel(DEFAULT_LABEL_KEY);
                    }}
                >
                    Delete
                </button>
                <button 
                    type="button"
                    className='to-edit'
                    onClick={ (e) => {
                        e.preventDefault();
                        onSetNavigation({currentNavigation: NAVIGATION[SIDE_MENU.MYDIARY].EDIT});
                    }}
                >
                    Edit
                </button>
            </div>
            <div className="mydiaries-details-info">
                {currentDiary.label.key !== DEFAULT_LABEL_KEY && 
                    <div className={`mydiaries-details-label label-item-color ${currentDiary.label.color}`} >
                        <i className="gg-tag"></i>
                        <span className='mydiaries-details-label-title'>{currentDiary.label.key}</span>
                    </div>
                }
                <span className="mydiaries-details-date">{formatDate(currentDiary.date)}</span>
            </div>
            <div className='mydiaries-details-content'>{currentDiary.details}</div>
        </div>
    );
}
export default MyDiaryDetail;