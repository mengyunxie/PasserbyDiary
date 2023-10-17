/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {
    SIDE_MENU,
    NAVIGATION,
} from './constants';
import PasserbyDetail from './PasserbyDetail';
import PasserbyList from './PasserbyList';

function PasserbyPanel({
    passerbyDiaries,
    previousNavigation,
    currentNavigation,
    currentDiary,
    onSetNavigation,
    onSetCurrentDiary,
    onGetMyPasserbyDiaries,
    onGetPasserbyDiaries,
}) {

    return (
        <div className='passerby-panel'>
            {currentNavigation === NAVIGATION[SIDE_MENU.PASSERBY].DETAIL &&  <PasserbyDetail 
                    previousNavigation={previousNavigation}
                    currentDiary={currentDiary}
                    onSetNavigation={onSetNavigation}
                    onGetMyPasserbyDiaries={onGetMyPasserbyDiaries}
                    onGetPasserbyDiaries={onGetPasserbyDiaries}
                />
            }
            {currentNavigation !== NAVIGATION[SIDE_MENU.PASSERBY].DETAIL &&  <PasserbyList 
                    passerbyDiaries={passerbyDiaries}
                    onSetNavigation={onSetNavigation}
                    onSetCurrentDiary={onSetCurrentDiary}
                />
            }
             
        </div>
    );
}
export default PasserbyPanel;