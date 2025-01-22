import React from 'react'
import { useNavigate } from 'react-router-dom';
import { Button } from 'antd';

const Welcome = () => {
    const navigate = useNavigate();
    const gotoDrivers = () => {
      navigate("/drivers");
    }

    return (
       <div className='center-content'>
            <img src='/img/f1_logo.png' alt='f1-logo' />
            <div className={'body-container'}>
                <div>Welcome to F1 Data!</div>
            </div>
            <div>Your home to search historical Formula One stats.</div>
            <div className={'home-button-container'}>
                <Button
                    type='primary'
                    onClick={gotoDrivers}
                    style={{ backgroundColor: 'green' }}
                >
                    Get Started
                </Button>
            </div>
      </div>
    )
}

export default Welcome
