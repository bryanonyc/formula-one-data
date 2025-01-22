import React from 'react'
import Tabs, { TAB_KEY_DRIVERS } from '../../components/Tabs'
import DriverList from './DriverList'

const DriverHome = () => {
  return (
    <Tabs activeTab={TAB_KEY_DRIVERS} children={<DriverList />}/>
  )
}

export default DriverHome
