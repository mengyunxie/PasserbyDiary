/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {SIDE_MENU} from './constants';
import PasserbyPanel from './PasserbyPanel';
import MyDiaryPanel from './MyDiaryPanel';
import SettingPanel from './SettingPanel';
import Status from './Status';
import Loading from './Loading';

function PanelControls({
    username, 
    avatar,
    avatars,
    labels,
    currentDiary,
    passerbyDiaries, 
    diaries,
    menu,
    previousNavigation,
    currentNavigation,  
    isDashBoardPending,
    error,
    onClearStatus,
    onSubmitDiary, 
    onDeleteDiary,
    onUpdateDiary,
    onUpdateAvatar,
    onSetNavigation,
    onSetCurrentDiary,
    onGetDiariesByLabel,
    onGetMyPasserbyDiaries,
    onGetPasserbyDiaries,
}) {

    if(isDashBoardPending) {
        return <Loading>Loading Data...</Loading>;
    }

    return (
        <div className="panel-controls">
            {error && <Status  error={error} onClearStatus={onClearStatus} />}
            {menu === SIDE_MENU.PASSERBY && <PasserbyPanel 
                    passerbyDiaries={passerbyDiaries}
                    previousNavigation={previousNavigation}
                    currentNavigation={currentNavigation}
                    currentDiary={currentDiary}
                    onSetCurrentDiary={onSetCurrentDiary}
                    onSetNavigation={onSetNavigation}
                    onGetMyPasserbyDiaries={onGetMyPasserbyDiaries}
                    onGetPasserbyDiaries={onGetPasserbyDiaries}
                />
            }
            {menu === SIDE_MENU.MYDIARY && <MyDiaryPanel 
                    diaries={diaries}
                    labels={labels}
                    previousNavigation={previousNavigation}
                    currentNavigation={currentNavigation}
                    currentDiary={currentDiary}
                    onSetCurrentDiary={onSetCurrentDiary}
                    onSetNavigation={onSetNavigation}
                    onSubmitDiary={onSubmitDiary}
                    onDeleteDiary={onDeleteDiary}
                    onUpdateDiary={onUpdateDiary}
                    onGetDiariesByLabel={onGetDiariesByLabel}
                />
            }
            {menu === SIDE_MENU.SETTING && <SettingPanel 
                    username={username} 
                    avatar={avatar}  
                    avatars={avatars}
                    currentNavigation={currentNavigation}
                    onUpdateAvatar={onUpdateAvatar}
                    onClearStatus={onClearStatus}
                />
            }
        </div>
    );
}
export default PanelControls;