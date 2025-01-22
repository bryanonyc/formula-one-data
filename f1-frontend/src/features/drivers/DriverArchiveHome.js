import React from 'react'
import DriverArchiveList from './DriverArchiveList'
import Tabs, { TAB_KEY_DRIVERS } from '../../components/Tabs'

const DriverArchiveHome = () => {
  return (
    <Tabs activeTab={TAB_KEY_DRIVERS} children={<DriverArchiveList />}/>
  )
}

export default DriverArchiveHome
