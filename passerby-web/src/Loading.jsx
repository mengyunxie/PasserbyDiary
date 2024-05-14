/*
 * Author: Mengyun Xie
 * Date: 04/17/2023
 * This code is a part of the final project of the INFO 6250 course
 */

function Loading({ className, children='Loading...' }) {
  return (
    <div className={`loading ${className}`} >
      <i className='gg-spinner-two'></i>
      <p className='loading-message'>{children}</p>
    </div>
  );
}

export default Loading;