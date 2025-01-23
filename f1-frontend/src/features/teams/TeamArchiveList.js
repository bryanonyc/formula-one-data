import { useQuery } from '@tanstack/react-query';
import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { SearchOutlined } from '@ant-design/icons';
import { Alert, Button, Input, Select, Space, Table, Typography  } from 'antd';
import { TEAM_NAMES_API_URL, TEAMS_ARCHIVE_API_URL } from '../api/api';
import BarChart from '../../components/BarChart';
import { pluck } from 'ramda';

const { Title } = Typography;

const TeamArchiveList = () => {
    const navigate = useNavigate();
    const [selectedTeam, setSelectedTeam] = useState(null);

    // Fetch specific team data to display charts
    const fetchChartData = async (selectedTeam) => {
        const response = await fetch(`${TEAMS_ARCHIVE_API_URL}?team=${selectedTeam}`);
        return response.json();
    };

    const { data: chartData } = useQuery({
      queryKey: ["chartData", selectedTeam],
      queryFn: () => fetchChartData(selectedTeam),
      // Enable only when a user selects a team from the dropdown
      enabled: !!selectedTeam
    });

    // Fetch all team names to display in the Select menu
    const fetchTeamNames = async () => {
      const nameResponse = await fetch(TEAM_NAMES_API_URL);
      return nameResponse.json();
    }

    const { data: selectData } = useQuery({
      queryKey: ['teamNamesData'],
      queryFn: fetchTeamNames,
    });

    // Fetch all the historical team data for main table
    const fetchData = async () => {
      const response = await fetch(TEAMS_ARCHIVE_API_URL);
      const json = await response.json();
      return json.map((r) => ({
        key: `${r.year}|${r.team}`,
        ...r
      }));
    };

    const { data: tableData, error } = useQuery({
      queryKey: ['teamArchiveData'],
      queryFn: fetchData,
    });

    const [, setSearchText] = useState('');
    const [, setSearchedColumn] = useState('');
    const searchInput = useRef(null);

    const handleSearch = (
        selectedKeys,
        confirm,
        dataIndex,
    ) => {
        confirm();
        setSearchText(selectedKeys[0]);
        setSearchedColumn(dataIndex);
    };

    const handleReset = (
        clearFilters,
        confirm
    ) => {
        clearFilters();
        setSearchText('');
        confirm();
    };

    const getColumnSearchProps = (dataIndex) => ({
        filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters, close }) => (
            <div style={{ padding: 8 }} onKeyDown={(e) => e.stopPropagation()}>
                <Input
                    ref={searchInput}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={(e) => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{ marginBottom: 8, display: 'block' }}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined />}
                        size="small"
                    >
                        Search
                    </Button>
                    <Button
                        onClick={() => clearFilters && handleReset(clearFilters, confirm)}
                        size="small"
                    >
                        Reset
                    </Button>
                    <Button
                        type="link"
                        size="small"
                        onClick={() => {
                            close();
                        }}
                    >
                        Close
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: (filtered) => (
          <SearchOutlined style={{ color: filtered ? '#1677ff' : undefined }} />
        ),
        onFilter: (value, record) =>
          record[dataIndex]
            .toString()
            .toLowerCase()
            .includes(value.toLowerCase()),
        filterDropdownProps: {
          onOpenChange(open) {
            if (open) {
                setTimeout(() => searchInput.current?.select(), 100);
              }
          }
        },
        render: (text) =>
          (
            text
          ),
      });

    const handleOnTeamSelection = (team) => {
      setSelectedTeam(team);
    };

    const columns = [
        {
            title: 'Year',
            dataIndex: 'year',
            key: 'year',
            ...getColumnSearchProps('year'),
            sorter: (a, b) => a.year - b.year,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Position',
            dataIndex: 'position',
            key: 'position',
            ...getColumnSearchProps('position'),
            sorter: (a, b) => a.position - b.position,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Team',
            dataIndex: 'team',
            key: 'team',
            ...getColumnSearchProps('team'),
            sorter: (a, b) => a.team.localeCompare(b.team),
            sortDirections: ['descend', 'ascend'],
            render: (text) => (
              <Button type="link" onClick={() => handleOnTeamSelection(text)} size='small'>
                {text}
              </Button>
            ),
        },
        {
            title: 'Points',
            dataIndex: 'points',
            key: 'points',
            sorter: (a, b) => a.points - b.points,
            sortDirections: ['descend', 'ascend'],
        }
    ];

    const handleNavigation = () => {
        navigate('/teams')
    };

    const clearSelect = () => {
      setSelectedTeam (null);
    }

    return (
        <>
        <Space direction='vertical' style={{width: '20%'}}>
          { selectData &&
              <Select
                showSearch
                value={selectedTeam}
                placeholder="Select Team Name"
                options={selectData}
                style={{width: '100%'}}
                onChange={handleOnTeamSelection}
              />
          }
          <Space >
            <Button type='primary' onClick={clearSelect}>
                Clear Select
              </Button>

            <Button type='primary' onClick={handleNavigation}>
              View Current Year
            </Button>
          </Space>
        </Space>

        <Title level={3}>{selectedTeam} Historical Data</Title>
        { error &&
            <Alert message={`Error: ${error.message}`} type='error'/>
        }
        { !selectedTeam && tableData &&
            <Table
                bordered
                dataSource={tableData}
                columns={columns}
                size='small'
            />
        }
        { selectedTeam && chartData &&
            <BarChart
              xLabel={pluck('year', chartData)}
              data={pluck('points', chartData)}
              datasetLabel="Points"
            />
        }

        </>
    )
};

export default TeamArchiveList
