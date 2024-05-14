/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { useState } from 'react';

function MyDiaryEdit({
    labels,
    currentDiary,
    previousNavigation,
    onSetNavigation,
    onUpdateDiary,
}) {

    const [diary, setDiary] = useState({
        details: currentDiary.details,
        labelKey: currentDiary.label.key,
        isPasserby: currentDiary.isPasserby,
    });

    function handleSubmit(e) {
        // Prevent the browser from reloading the page
        e.preventDefault();
        if(diary.details) {
            onUpdateDiary({id: currentDiary.id, details: diary.details, labelKey: diary.labelKey, isPasserby: diary.isPasserby });
        }
    }

    return (
        <form 
            method="post" 
            onSubmit={handleSubmit} 
            className='mydiaries-form'
        >
            <div className='mydiaries-form-tools'>
                <button 
                    type="button"
                    className='to-cancel'
                    onClick={ (e) => {
                        e.preventDefault();
                        // Go to the details of the diary page 
                        onSetNavigation({currentNavigation: previousNavigation});
                    }}
                >
                    <i className="gg-close"></i>
                    <span className='to-cancel-title'>Cancel</span>
                </button>
                <button 
                    type="submit"
                    className='to-submit'
                    disabled={!diary.details}
                >
                    <i className="gg-check"></i>
                    <span className='to-submit-title'>Save</span>
                </button>
            </div>    
            <div className="mydiaries-form-row">
                <span className='mydiaries-form-title'>Select Type: </span>
                <select 
                    className='mydiaries-form-select'
                    defaultValue={diary.labelKey}
                    onChange={ (e) => {
                        e.preventDefault();
                        setDiary({...diary, labelKey: e.target.value});
                    }}
                >
                    { Object.values(labels).map( label => (
                        <option 
                            key={label.key}
                            value={label.key}
                        >
                            {label.key}
                        </option>
                    ))}
                </select>
            </div>
            <div className='mydiaries-form-row'>
                <span className='mydiaries-form-title'>Post to Passerby: </span>
                <input 
                    type="checkbox" 
                    name="isPasserby" 
                    className='mydiaries-form-checkbox'
                    checked={diary.isPasserby} 
                    onChange={e => setDiary({...diary, isPasserby: e.target.checked})} 
                />
            </div>
            <div className='mydiaries-form-row form-textarea'>
                <span className='mydiaries-form-title'>Write your diary (Max 3000 Letter): </span>  
                <textarea 
                    name="diary"
                    className='mydiaries-form-input'
                    placeholder='Enter your diary here ...'
                    rows={20} 
                    maxLength={3000}
                    value={diary.details} 
                    onChange={e => setDiary({...diary, details: e.target.value})}
                />
            </div>
        </form>
    );
}
export default MyDiaryEdit;