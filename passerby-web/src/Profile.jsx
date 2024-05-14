/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { useState } from 'react';
import Avatar from './Avatar';

function Profile({
    username,
    avatar,
    avatars,
    onUpdateAvatar,
    onClearStatus,
}) {

    const [isEditing, setIsEditing] = useState(false);
    const [newAvatar, setNewAvatar] = useState(avatar);
    return (
        <div className="profile">
            <div className="profile-username">
                <span className="profile-title">Username: </span>
                <span>{username}</span>
            </div>
            {!isEditing && <div className="profile-avatar">
                    <span className="profile-title">Your Avatar: </span>
                    <Avatar avatar={newAvatar} />
                    <button 
                        type="button"
                        className='profile-avatar-to-edit'
                        onClick={ (e) => {
                            e.preventDefault();
                            onClearStatus();
                            setIsEditing(true);
                        }}
                    >
                        Change
                    </button>     
                </div>
            } 
            {isEditing &&
                <div className='profile-avatars'>
                    <p className="profile-avatars-title">Select Avatar: </p> 
                    <ul className='avatar-list'>
                        { Object.values(avatars).map( item => (
                            <li 
                                className='avatar-item'
                                key={item}
                                onClick={ (e) => {
                                    e.preventDefault();
                                    setNewAvatar(item);
                                }}
                            >
                                <Avatar avatar={item} />
                                { item === newAvatar && <i className="gg-check"></i> }
                            </li>
                        ))}
                    </ul>
                    <div className='profile-avatars-tools'>
                        <button 
                            type="button"
                            className='profile-avatars-to-cancel'
                            onClick={ (e) => {
                                e.preventDefault();
                                setIsEditing(false);
                                setNewAvatar(avatar);
                            }}
                        >
                            Cancel
                        </button>
                        <button 
                            type="button"
                            className='profile-avatars-to-submit'
                            onClick={ (e) => {
                                e.preventDefault();
                                setIsEditing(false);
                                onUpdateAvatar(newAvatar);
                            }}
                        >
                            Save
                        </button>
                    </div>
                </div>
            }
        </div> 
    );
}
export default Profile;