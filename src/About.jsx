/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

function About() {
    return (
      <div className='about'>
        <p className="about-title">Passerby Diary</p>
        <p className="about-purport">Only diaries posted for Passerby we see,<br />
          Perhaps you long to comment, to leave your plea,<br />
          Yet we are all mere passersby,<br />
          Transient souls, who quickly fly.<br />
        </p>
        <div className="about-intro">
          <p className="about-intro-title">Passerby</p>
          <p className="about-intro-details">
            Here you can see all the diaries from around the world that have been posted to the 'Passerby' section. 
            You can also see your passerby diaries.
            If you post a diary to 'Passerby' section, it means it will be open to the world.
            But passers-by can only view your passerby diary, and they can't comment or connect with you. Because we are all travellers after all.
          </p>
        </div>
        <div className="about-intro">
          <p className="about-intro-title">MyDiary</p>
          <p className="about-intro-details">
            Here you can add, view, update and delete your diaries.
            When you edit your diaries, you can choose to post or not post to 'Passerby' section.
          </p>
        </div>
        <div className="about-intro">
          <p className="about-intro-title">Setting</p>
          <p className="about-intro-details">
            Here you can view your profile and update your avatar.
          </p>
        </div>
      </div>
    );
  }
  
  export default About;