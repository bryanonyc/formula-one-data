import React from 'react'
import Tabs, { TAB_KEY_TEAMS } from '../../components/Tabs'
import TeamArchiveList from './TeamArchiveList'

const TeamArchiveHome = () => {
  return (
    <Tabs activeTab={TAB_KEY_TEAMS} children={<TeamArchiveList />} />
  )
}

export default TeamArchiveHome
