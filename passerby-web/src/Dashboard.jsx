/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import SideMenu from './SideMenu';
import NavigationControls from './NavigationControls';
import PanelControls from './PanelControls';
import Avatar from './Avatar';

function Dashboard({
  username, 
  avatar, 
  labels, 
  avatars, 
  currentDiary,
  passerbyDiaries, 
  diaries,
  menu,
  error,
  isDashBoardPending,
  previousNavigation,
  currentNavigation,
  onSubmitDiary,
  onDeleteDiary,
  onUpdateDiary,
  onUpdateAvatar,
  onSetMenu, 
  onSetNavigation,
  onLogout,
  onClearStatus,
  onSetCurrentDiary,
  onGetDiariesByLabel,
  onGetMyPasserbyDiaries,
  onGetPasserbyDiaries,
}) {
  return (
    <main className="dashboard">
      <div className='dashboard-user'>
        <Avatar avatar={avatar} username={username} />
      </div>
      <SideMenu
        menu={menu}
        onSetMenu={onSetMenu}
        onGetDiariesByLabel={onGetDiariesByLabel}
        onGetPasserbyDiaries={onGetPasserbyDiaries}
      />
      <NavigationControls 
        labels={labels}
        menu={menu}
        currentNavigation={currentNavigation}
        onLogout={onLogout}
        onSetNavigation={onSetNavigation}
        onGetDiariesByLabel={onGetDiariesByLabel}
        onGetMyPasserbyDiaries={onGetMyPasserbyDiaries}
        onGetPasserbyDiaries={onGetPasserbyDiaries}
      />
      <PanelControls 
        username={username} 
        avatar={avatar} 
        labels={labels} 
        avatars={avatars} 
        menu={menu}
        error={error}
        previousNavigation={previousNavigation}
        currentNavigation={currentNavigation}
        passerbyDiaries={passerbyDiaries} 
        diaries={diaries}
        isDashBoardPending={isDashBoardPending}
        currentDiary={currentDiary}
        onSetNavigation={onSetNavigation}
        onSetCurrentDiary={onSetCurrentDiary}
        onSubmitDiary={onSubmitDiary}
        onDeleteDiary={onDeleteDiary}
        onUpdateDiary={onUpdateDiary}
        onUpdateAvatar={onUpdateAvatar}
        onClearStatus={onClearStatus}
        onGetDiariesByLabel={onGetDiariesByLabel}
        onGetMyPasserbyDiaries={onGetMyPasserbyDiaries}
        onGetPasserbyDiaries={onGetPasserbyDiaries}
      />
    </main>
  );
}

export default Dashboard;