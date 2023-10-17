/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

import { MESSAGES } from './constants';

function Status({ error, onClearStatus }) {
  const message = MESSAGES[error] || MESSAGES.default;
  return (
    <div className="status">
      {error && 
        <>
          <span className="status-message">{message}</span>
          <button 
            type="button"
            className="status-delete"
            onClick={onClearStatus}
          >
            X
          </button>
        </>
      }
    </div>
  );
}

export default Status;
