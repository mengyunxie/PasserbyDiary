/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * Description: Offer build-in labels.
 * This code is a part of the final project of the INFO 6250 course.
 */

const LABEL_COLOR = {
    ROSE: 'rose',
    ORANGE: 'orange',
    YELLOW: 'yellow',
    MEDALLION: 'medallion',
    TAN: 'tan',
    SALT: 'salt',
    FROST: 'frost',
    WATERMELON: 'watermelon',
    PURPLE: 'purple',
    BLUE: 'blue',
    OCEAN: 'ocean',
    GREEN: 'green',
    COFFEE: 'coffee',
    GREY: 'grey',
    IRIS: 'iris',
    SKY: 'sky',
};

const LABEL_TYPE = {
    DEFAULT: 'Default',
    CUSTOM: 'Custom'
};

// The system's built-in 15 labels and a default 'all' label
const LABEL_KEY = {
    ALL: 'all',
    TRAVEL: 'travel',
    FOOD: 'food',
    HEALTH: 'health',
    WORK: 'work',
    ACADEMIC: 'academic',
    FINANCIAL: 'financial',
    MOOD: 'mood',
    DREAM: 'dream',
    TODO: 'todo',
    SCHOOL: 'school',
    INTEREST: 'interest',
    READING: 'reading',
    GRATITUDE: 'gratitude',
    IDEAS: 'ideas',
    MOVIES: 'movies'
};

const labels = {
    [LABEL_KEY.ALL]: {
        key: LABEL_KEY.ALL,
        color: LABEL_COLOR.ROSE,
        type: LABEL_TYPE.DEFAULT
    },
    [LABEL_KEY.TRAVEL]: {
        key: LABEL_KEY.TRAVEL,
        color: LABEL_COLOR.ORANGE,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.FOOD]: {
        key: LABEL_KEY.FOOD,
        color: LABEL_COLOR.YELLOW,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.HEALTH]: {
        key: LABEL_KEY.HEALTH,
        color: LABEL_COLOR.MEDALLION,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.WORK]: {
        key: LABEL_KEY.WORK,
        color: LABEL_COLOR.TAN,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.ACADEMIC]: {
        key: LABEL_KEY.ACADEMIC,
        color: LABEL_COLOR.SALT,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.FINANCIAL]: {
        key: LABEL_KEY.FINANCIAL,
        color: LABEL_COLOR.FROST,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.MOOD]: {
        key: LABEL_KEY.MOOD,
        color: LABEL_COLOR.WATERMELON,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.DREAM]: {
        key: LABEL_KEY.DREAM,
        color: LABEL_COLOR.PURPLE,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.TODO]: {
        key: LABEL_KEY.TODO,
        color: LABEL_COLOR.BLUE,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.SCHOOL]: {
        key: LABEL_KEY.SCHOOL,
        color: LABEL_COLOR.OCEAN,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.INTEREST]: {
        key: LABEL_KEY.INTEREST,
        color: LABEL_COLOR.GREEN,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.READING]: {
        key: LABEL_KEY.READING,
        color: LABEL_COLOR.COFFEE,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.GRATITUDE]: {
        key: LABEL_KEY.GRATITUDE,
        color: LABEL_COLOR.GREY,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.IDEAS]: {
        key: LABEL_KEY.IDEAS,
        color: LABEL_COLOR.IRIS,
        type: LABEL_TYPE.CUSTOM
    },
    [LABEL_KEY.MOVIES]: {
        key: LABEL_KEY.MOVIES,
        color: LABEL_COLOR.SKY,
        type: LABEL_TYPE.CUSTOM
    }
}

function getDefaultLabel() {
    return labels[LABEL_KEY.ALL];
}

function getLabel(key) {
    return labels[key];
}

function getLabels() {
    return labels;
}

function isValid(key) {
return !!labels[key];
}

module.exports = {
    getDefaultLabel,
    getLabel,
    getLabels,
    isValid,
  };
