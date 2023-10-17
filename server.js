/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * Description: Offer rest API for this system
 * This code is a part of the final project of the INFO 6250 course
 */

const express = require('express');
const cookieParser = require('cookie-parser');

const app = express();
const PORT = process.env.PORT || 3000;

const sessions = require('./sessions');
const users = require('./users'); // The users data
const diaries = require('./diaries'); // The diary data
const avatars = require('./avatars'); // The system's built-in avatars
const labels = require('./labels'); // The system's built-in labels

app.use(cookieParser());
app.use(express.static('./build'));
app.use(express.json()); // Parses requests with json content bodies

/* Check for existing session (used on page load) */
app.get('/api/v1/session', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }
  const user = users.getUser(username);
  res.json(user);
});

/* Create a new session (login) */
app.post('/api/v1/session', (req, res) => {
  const { username } = req.body;

  if(!username) { // The username is empty
    res.status(400).json({ error: 'required-username' });
    return;
  }
  if(!users.isValidUsername(username)) {
    res.status(400).json({ error: 'invalid-username' });
    return;
  }
  if(username === 'dog') {
    res.status(403).json({ error: 'auth-insufficient' });
    return;
  }

  const existingUserData = users.getUser(username);

  if(!existingUserData) { // New user, create default labels and avatars for this user
    const defaultAvatar = avatars.getDefaultAvatar();
    const avatarList = avatars.getAvatars();
    const labelList = labels.getLabels();
    users.createUser({username, defaultAvatar, avatars: avatarList, labels: labelList});
  }

  const sid = sessions.addSession(username);
  res.cookie('sid', sid);
  res.json(users.getUser(username));
});

/* Update user's avatar */
app.patch('/api/v1/session', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !users.isValidUsername(username)) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }
  const {avatar} = req.body;

  if(!avatars.isValid(avatar)) { // Check if this avatar is a system's built-in avatar
    res.status(400).json({ error: 'invalid-avatar' });
    return;
  }

  users.updateUserAvatar({username, avatar}); // Update the user's avatar
  diaries.updateDiariesUserAvatar({username, avatar}); // Update the user's avatar of the diaries object

  res.json(users.getUser(username));
});

/* Logout */
app.delete('/api/v1/session', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';
  if(sid) {
    res.clearCookie('sid');
  }
  if(username) {
    // Delete the session, but not the user data
    sessions.deleteSession(sid);
  }
  res.json({ wasLoggedIn: !!username }); // Provides some extra info that can be safely ignored
});

/* Add a new diary */
app.post('/api/v1/diaries', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }
  const {details, labelKey, isPasserby} = req.body;

  if(!details) { // Details of a diary is empty
    res.status(400).json({ error: 'required-diary-details' });
    return;
  }

  if(!diaries.isValid(details)) {
    res.status(400).json({ error: 'invalid-diary-details' });
    return;
  }

  if(!labels.isValid(labelKey)) { // Check if this label is a system's built-in label
    res.status(400).json({ error: 'invalid-label' });
    return;
  }

  const user = users.getUser(username);

  const label = labels.getLabel(labelKey);
  const id = diaries.addDiary({user, label, isPasserby, details});

  res.json(diaries.getDiary(id));
});

/* Update user's diary */
app.patch('/api/v1/diaries/:id', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !users.isValidUsername(username)) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  const { id } = req.params;
  const {details, labelKey, isPasserby} = req.body;

  if(!details) { // Details of a diary is empty
    res.status(400).json({ error: 'required-diary-details' });
    return;
  }

  if(!diaries.isValid(details)) {
    res.status(400).json({ error: 'invalid-diary-details' });
    return;
  }

  if(!labels.isValid(labelKey)) { // Check if this label is a system's built-in label
    res.status(400).json({ error: 'invalid-label' });
    return;
  }

  if(!diaries.contains(id)) { // This diary id is not exist
    res.status(404).json({ error: `noSuchDiaryId`, message: `No diary with id ${id}` });
    return;
  }

  const diary = diaries.getDiary(id);

  if(diary.username != username) { // Check if the User id is match diary id
    res.status(404).json({ error: `notMatchUser`, message: `User id is not match diary id` });
    return;
  }
  const label = labels.getLabel(labelKey);
  diaries.updateDiary({id, label, isPasserby, details});
  res.json(diaries.getDiary(id));
});

/* Delete user's diary */
app.delete('/api/v1/diaries/:id', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !users.isValidUsername(username)) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }
  const { id } = req.params;
  const exists = diaries.contains(id);
  if(exists) { 
    diaries.deleteDiary(id);
  }
  res.json({ message: exists ? `diary ${id} deleted` : `Diary ${id} did not exist` });
});

/* Get this user's diaries by different labels */
app.get('/api/v1/diariesbylabel/:label', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  const { label } = req.params;

  if(!labels.isValid(label)) { // Check if this label is a system's built-in label
    res.status(400).json({ error: 'invalid-label' });
    return;
  }

  let response;
  if(!label || label == labels.getDefaultLabel().key) { // Get all labels' diaries
    response = diaries.getDiaries(username);
  } else { // Get this label's diaries
    response = diaries.getDiariesByLabel({username, label});
  }
  
  res.json(response);
});

/* Get all passersby's diaries */
app.get('/api/v1/passersby/all', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  res.json(diaries.getPasserbyDiaries());
});

/* Get this user's passersby's diaries */
app.get('/api/v1/passersby/mine', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if(!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  res.json(diaries.getMinePasserbyDiaries(username));
});

app.listen(PORT, () => console.log(`http://localhost:${PORT}`));