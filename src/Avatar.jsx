/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * These avatar images are from https://www.iconfinder.com
 * This code is a part of the final project of the INFO 6250 course
 */

import {AVATARS_KEY} from './constants';

import Default from './assets/avatar.png';
import Boy from './assets/avatar-boy.png';
import Girl from './assets/avatar-girl.png';
import Female from './assets/avatar-female.png';
import Male from './assets/avatar-male.png';
import Grandma from './assets/avatar-grandma.png';
import Grandpa from './assets/avatar-grandpa.png';
import Cat from './assets/avatar-cat.png';
import Bear from './assets/avatar-bear.png';
import Deer from './assets/avatar-deer.png';
import Dog from './assets/avatar-dog.png';
import Fox from './assets/avatar-fox.png';
import Happy from './assets/avatar-happy.png';
import Wonder from './assets/avatar-wonder.png';
import Batman from './assets/avatar-batman.png';

function Avatar({avatar, username }) {
    let url = Default;
    switch(avatar) {
        case AVATARS_KEY.DEFAULT:
            url = Default;
            break;
        case AVATARS_KEY.BOY:
            url = Boy;
            break;
        case AVATARS_KEY.GIRL:
            url = Girl;
            break;
        case AVATARS_KEY.FEMALE:
            url = Female;
            break;
        case AVATARS_KEY.MALE:
            url = Male;
            break;
        case AVATARS_KEY.GRANDMA:
            url = Grandma;
            break;
        case AVATARS_KEY.GRANDPA:
            url = Grandpa;
            break;
        case AVATARS_KEY.CAT:
            url = Cat;
            break;
        case AVATARS_KEY.BEAR:
            url = Bear;
            break;
        case AVATARS_KEY.DEER:
            url = Deer;
            break;
        case AVATARS_KEY.DOG:
            url = Dog;
            break;
        case AVATARS_KEY.FOX:
            url = Fox;
            break;
        case AVATARS_KEY.HAPPY:
            url = Happy;
            break;
        case AVATARS_KEY.WONDER:
            url = Wonder;
            break;
        case AVATARS_KEY.BATMAN:
            url = Batman;
            break;
        default:
            url = Default;
    }

    return (
        <div className="avatar-info" >
            <img 
                src={url}
                className="avatar-img" 
                alt="avatar"
            />
            {username && <span className="avatar-username">{username}</span>}
        </div>
    );
}
export default Avatar;