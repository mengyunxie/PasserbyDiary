/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import {
    SIDE_MENU,
    NAVIGATION,
} from './constants';

import Profile from './Profile';
import About from './About';

function SettingPanel({
    username,
    avatar,
    avatars,
    currentNavigation,
    onUpdateAvatar,
    onClearStatus,
}) {
    return (
        <div className='setting-panel'>
            {currentNavigation === NAVIGATION[SIDE_MENU.SETTING].DEFAULT && <Profile
                    username={username}
                    avatar={avatar}
                    avatars={avatars}
                    onUpdateAvatar={onUpdateAvatar}
                    onClearStatus={onClearStatus}
                />
            }
            {currentNavigation === NAVIGATION[SIDE_MENU.SETTING].ABOUT && <About />}
        </div>
    );
}
export default SettingPanel;