import { useQuery } from '@tanstack/react-query';
import React, { useCallback, useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { SearchOutlined } from '@ant-design/icons';
import { Alert, Button, Input, Space, Spin, Table, Typography } from 'antd';
import { DRIVERS_API_URL, DRIVERS_UPDATE_API_URL } from '../api/api';
const { Title } = Typography;

const DriverList = () => {
    const [performFetchUpdate, setPerformFetchUpdate] = useState(false);

    const navigate = useNavigate();

    const fetchData = async () => {
        const response = await fetch(DRIVERS_API_URL);
        const json = await response.json();
        json.forEach((element) => {
            element.key = element.driver;
            const [day, month, year] = element.dateOfBirth.split('/');
            element.dateOfBirth = new Date(
                year,
                month - 1,
                day
            ).toLocaleDateString();
        });
        return json;
    };

    const { data, refetch, isLoading, error } = useQuery({
        queryKey: ['driverData'],
        queryFn: fetchData,
    });

    const fetchUpdates = useCallback(async () => {
        const response = await fetch(DRIVERS_UPDATE_API_URL);
        const json = await response.json();
        setPerformFetchUpdate(false);
        refetch();
        json.forEach((element) => {
            element.key = element.driver;
            const [day, month, year] = element.dateOfBirth.split('/');
            element.dateOfBirth = new Date(
                year,
                month - 1,
                day
            ).toLocaleDateString();
        });
        return json;
    }, []);

    const { isLoading: isLoadingUpdates } = useQuery({
        queryKey: ['driverData'],
        queryFn: fetchUpdates,
    });

    const [, setSearchText] = useState('');
    const [, setSearchedColumn] = useState('');
    const searchInput = useRef(null);

    const handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        setSearchText(selectedKeys[0]);
        setSearchedColumn(dataIndex);
    };

    const handleReset = (clearFilters, confirm) => {
        clearFilters();
        setSearchText('');
        confirm();
    };

    const getColumnSearchProps = (dataIndex) => ({
        filterDropdown: ({
            setSelectedKeys,
            selectedKeys,
            confirm,
            clearFilters,
            close,
        }) => (
            <div style={{ padding: 8 }} onKeyDown={(e) => e.stopPropagation()}>
                <Input
                    ref={searchInput}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={(e) =>
                        setSelectedKeys(e.target.value ? [e.target.value] : [])
                    }
                    onPressEnter={() =>
                        handleSearch(selectedKeys, confirm, dataIndex)
                    }
                    style={{ marginBottom: 8, display: 'block' }}
                />
                <Space>
                    <Button
                        type='primary'
                        onClick={() =>
                            handleSearch(selectedKeys, confirm, dataIndex)
                        }
                        icon={<SearchOutlined />}
                        size='small'
                    >
                        Search
                    </Button>
                    <Button
                        onClick={() =>
                            clearFilters && handleReset(clearFilters, confirm)
                        }
                        size='small'
                    >
                        Reset
                    </Button>
                    <Button
                        type='link'
                        size='small'
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
            <SearchOutlined
                style={{ color: filtered ? '#1677ff' : undefined }}
            />
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
            },
        },
        render: (text) => text,
    });

    const columns = [
        {
            title: 'Driver',
            dataIndex: 'driver',
            key: 'driver',
            ...getColumnSearchProps('driver'),
            sorter: (a, b) => a.driver.localeCompare(b.driver),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Team',
            dataIndex: 'team',
            key: 'team',
            ...getColumnSearchProps('team'),
            sorter: (a, b) => a.team.localeCompare(b.team),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Country',
            dataIndex: 'country',
            key: 'country',
            ...getColumnSearchProps('country'),
            sorter: (a, b) => a.country.localeCompare(b.country),
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Podiums',
            dataIndex: 'podiums',
            key: 'podiums',
            sorter: (a, b) => a.podiums - b.podiums,
            sortDirections: ['descend', 'ascend'],
        },
        {
            title: 'Grands Prix Entered',
            dataIndex: 'grandsPrixEntered',
            key: 'grandsPrixEntered',
            sorter: (a, b) => a.grandsPrixEntered - b.grandsPrixEntered,
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
            title: 'Higest Grid Position',
            dataIndex: 'highestGridPosition',
            key: 'highestGridPosition',
        },
        {
            title: 'Date Of Birth',
            dataIndex: 'dateOfBirth',
            key: 'dateOfBirth',
        },
        {
            title: 'Place Of Birth',
            dataIndex: 'placeOfBirth',
            key: 'placeOfBirth',
        },
    ];

    const handleNavigation = () => {
        navigate('/drivers/archive');
    };

    const handleUpdateRequest = async () => {
        setPerformFetchUpdate(true);
    };

    useEffect(() => {
        if (performFetchUpdate) {
            fetchUpdates();
        }
    }, [performFetchUpdate, fetchUpdates]);

    return (
        <>
            <Space direction='vertical'>
                <Space>
                    <Button
                        type='primary'
                        onClick={handleUpdateRequest}
                        disabled={isLoading || performFetchUpdate}
                    >
                        Fetch Updates
                    </Button>
                    <Button
                        type='primary'
                        onClick={handleNavigation}
                        disabled={isLoading || performFetchUpdate}
                    >
                        View Historical Data
                    </Button>
                </Space>

                <Title level={3}>Current Year</Title>
            </Space>
            {isLoading && (
                <div className='center-content'>
                    <Spin />
                </div>
            )}
            {error && (
                <Alert message={`Error: ${error.message}`} type='error' />
            )}
            {data && (
                <Table
                    bordered
                    dataSource={data}
                    columns={columns}
                    size='small'
                    scroll={{ y: 500 }}
                    loading={performFetchUpdate}
                    pagination={{
                        showSizeChanger: true,
                    }}
                />
            )}
        </>
    );
};

export default DriverList;
