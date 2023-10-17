/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

const users = {};

/*  Create a default user data for new users */
function createUser( {username, defaultAvatar, avatars, labels} ) {
  users[username] = {
    username,
    avatar: defaultAvatar,
    avatars,
    labels,
  };
}

function updateUserAvatar( {username, avatar} ) {
  users[username].avatar = avatar;
  return users[username];
}

function getUsers() {
  return users;
}

function getUser(username) {
  return users[username];
}

function isValidUsername(username) {
  let isValid = true;
  isValid = !!username && username.trim();
  isValid = isValid && username.match(/^[A-Za-z0-9]{1,20}$/);
  return isValid;
}

module.exports = {
  isValidUsername,
  getUsers,
  createUser,
  getUser,
  updateUserAvatar,
};
