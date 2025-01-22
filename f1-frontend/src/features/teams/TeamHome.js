import React from 'react'
import Tabs, { TAB_KEY_TEAMS } from '../../components/Tabs'
import TeamList from './TeamList'

const TeamHome = () => {
  return (
    <Tabs activeTab={TAB_KEY_TEAMS} children={<TeamList />}/>
  )
}

export default TeamHome
