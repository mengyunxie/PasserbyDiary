/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { SIDE_MENU, NAVIGATION } from './constants';
import MyDiaryAdd from './MyDiaryAdd';
import MyDiaryDetail from './MyDiaryDetail';
import MyDiaryList from './MyDiaryList';
import MyDiaryEdit from './MyDiaryEdit';

function MyDiaryPanel({
    labels,
    diaries,
    previousNavigation,
    currentNavigation,
    currentDiary,
    onSubmitDiary,
    onDeleteDiary,
    onUpdateDiary,
    onSetCurrentDiary,
    onSetNavigation,
    onGetDiariesByLabel,
}) {

    return (
        <div className='mydiary-panel'>
            {currentNavigation === NAVIGATION[SIDE_MENU.MYDIARY].DETAIL &&  <MyDiaryDetail 
                    currentDiary={currentDiary}
                    onSetNavigation={onSetNavigation}
                    onDeleteDiary={onDeleteDiary}
                    onGetDiariesByLabel={onGetDiariesByLabel}
                />
            }
            {currentNavigation === NAVIGATION[SIDE_MENU.MYDIARY].EDIT &&  <MyDiaryEdit 
                    labels={labels}
                    currentDiary={currentDiary}
                    previousNavigation={previousNavigation}
                    onSetNavigation={onSetNavigation}
                    onUpdateDiary={onUpdateDiary}
                />
            }
            {currentNavigation === NAVIGATION[SIDE_MENU.MYDIARY].DEFAULT &&  <MyDiaryList 
                    diaries={diaries}
                    onSetNavigation={onSetNavigation}
                    onSetCurrentDiary={onSetCurrentDiary}
                />
            }
            {currentNavigation === NAVIGATION[SIDE_MENU.MYDIARY].ADD &&  <MyDiaryAdd 
                    labels={labels}
                    previousNavigation={previousNavigation}
                    onSetNavigation={onSetNavigation}
                    onSubmitDiary={onSubmitDiary}
                    onGetDiariesByLabel={onGetDiariesByLabel}
                />
            }
        </div>
    );
}
export default MyDiaryPanel;