import React from 'react';
import Header from '../components/Header';
import styles  from '../assets/css/layout.css';

const Home = () => {

    const onClickTest = () => {
        console.log(localStorage.getItem('jwtToken'))
    }

    return (
        <>
        <Header />
        <div className={styles.centerContent}>
            메인화면
        </div>
        <button onClick={(e)=> onClickTest()}>토큰</button>
    </>
    );
};

export default Home;