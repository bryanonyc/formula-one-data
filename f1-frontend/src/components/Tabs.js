import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Tabs as AntdTabs } from 'antd'

export const TAB_KEY_DRIVERS = 'drivers';
export const TAB_KEY_TEAMS = 'teams';

const Tabs = ({activeTab, children})=> {
    const navigate = useNavigate();
    const items = [
        {
          key: TAB_KEY_DRIVERS,
          label: 'Drivers',
          children: (children),
        },
        {
          key: TAB_KEY_TEAMS,
          label: 'Teams',
          children: (children),
        }
    ]

    const onChange = (key) => {
        navigate(`/${key}`)
    }
    return (
        <AntdTabs items={items} onChange={onChange} defaultActiveKey={activeTab} />
    )
}

export default Tabs
