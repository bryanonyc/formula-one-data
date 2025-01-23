import { useQuery } from '@tanstack/react-query';
import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { SearchOutlined } from '@ant-design/icons';
import { Alert, Button, Input, Space, Spin, Table, Typography  } from 'antd';
import { TEAMS_API_URL } from '../api/api';

const { Title } = Typography;

const TeamList = () => {
    const navigate = useNavigate();

    const fetchData = async () => {
        const response = await fetch(TEAMS_API_URL);
        const json = await response.json();
        return json.map((r) => ({
        key: `${r.fullTeamName}`,
        ...r
        }));
    };

    const { data, isLoading, error } = useQuery({
        queryKey: ['teamData'],
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

    const columns = [
        {
            title: 'Full Team Name',
            dataIndex: 'fullTeamName',
            key: 'fullTeamName',
            ...getColumnSearchProps('fullTeamName'),
            sorter: (a, b) => a.fullTeamName.localeCompare(b.fullTeamName),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Base',
            dataIndex: 'base',
            key: 'base',
            ...getColumnSearchProps('base'),
            sorter: (a, b) => a.base.localeCompare(b.base),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Team Chief',
            dataIndex: 'teamChief',
            key: 'teamChief',
            ...getColumnSearchProps('teamChief'),
            sorter: (a, b) => a.teamChief.localeCompare(b.teamChief),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Chassis',
            dataIndex: 'chassis',
            key: 'chassis',
        },
        {
            title: 'Power Unit',
            dataIndex: 'powerUnit',
            key: 'powerUnit',
        },
        {
            title: 'First Team Entry',
            dataIndex: 'firstTeamEntry',
            key: 'firstTeamEntry',
            sorter: (a, b) => a.firstTeamEntry - b.firstTeamEntry,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'World Championships',
            dataIndex: 'worldChampionships',
            key: 'worldChampionships',
            sorter: (a, b) => a.worldChampionships - b.worldChampionships,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Highest Race Finish',
            dataIndex: 'highestRaceFinish',
            key: 'highestRaceFinish',
        },
        {
            title: 'Pole Positions',
            dataIndex: 'polePositions',
            key: 'polePositions',
            sorter: (a, b) => a.polePositions - b.polePositions,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Fastest Laps',
            dataIndex: 'fastestLaps',
            key: 'fastestLaps',
            sorter: (a, b) => a.fastestLaps - b.fastestLaps,
            sortDirections: ['descend', 'ascend'],
        }
    ];

    const handleNavigation = () => {
        navigate('/teams/archive')
    };

    return (
        <>
        <Space direction='vertical'>
            <Button type="primary" onClick={handleNavigation}>
                View Historical Data
            </Button>
            <Title level={3}>Current Year</Title>
        </Space>
        { isLoading &&
          <div className='center-content'>
            <Spin />
          </div>
        }
        { error &&
            <Alert message={`Error: ${error.message}`} type='error'/>
        }
        { data &&
            <Table
                bordered
                dataSource={data}
                columns={columns}
                size='small'
                scroll={{ y: 500}}
                pagination={{
                    showSizeChanger: true
                }}
            />
        }
        </>
    )
};

export default TeamList
