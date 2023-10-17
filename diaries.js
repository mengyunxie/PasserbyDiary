/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

const uuid = require('uuid').v4;

const diaries = {};

function contains(id) {
    return !!diaries[id];
}

function isValid(details) {
    let isValid = true;
    isValid = !!details && details.trim();
    isValid = isValid && details.length <= 3000;
    return isValid;
}

function addDiary({user, label, isPasserby, details}) {
    const id = uuid();
    const date = Date.now();
    // The intro attribute is the first 50 letters of the details
    const intro = details.length > 50 ? `${details.substring(0, 50)}...` : details;
    
    diaries[id] = {
        id,
        username: user.username,
        avatar: user.avatar,
        date,
        label,
        isPasserby, 
        details,
        intro,
    };

    return id;
}

function deleteDiary(id) {
    delete diaries[id];
}

function getDiary(id) {
    return diaries[id];
}

function updateDiary({id, label, isPasserby, details}) {
    diaries[id].label = label;
    diaries[id].isPasserby = isPasserby;
    diaries[id].details = details;
    diaries[id].intro = details.length > 50 ? `${details.substring(0, 50)}...` : details;
}

/* Update the avatar of all diaries of this username */
function updateDiariesUserAvatar({username, avatar}) {
    Object.values(diaries).forEach(diary => {
        if (diary.username === username) {
            diary.avatar = avatar;
        }
    });
}

/* Get this user's all diaries by chronological order */
function getDiaries(username) {
    return Object.values(diaries).filter((item) => item?.username == username).sort((item1,item2) => item2.date - item1.date);
}

function getDiariesByLabel({username, label}) {
    return Object.values(diaries).filter((item) => item?.username == username && item?.label.key == label).sort((item1,item2) => item2.date - item1.date);
}

/* Get all passerby diaries by chronological order */
function getPasserbyDiaries() {
    return Object.values(diaries).filter((item) => item?.isPasserby).sort((item1,item2) => item2.date - item1.date);
}

function getMinePasserbyDiaries(username) {
    return Object.values(diaries).filter((item) => item?.isPasserby && item?.username == username).sort((item1,item2) => item2.date - item1.date);
}

module.exports = {
    contains,
    isValid,
    addDiary,
    deleteDiary,
    getDiaries,
    getDiary,
    updateDiary,
    getPasserbyDiaries,
    getMinePasserbyDiaries,
    getDiariesByLabel,
    updateDiariesUserAvatar,
  };