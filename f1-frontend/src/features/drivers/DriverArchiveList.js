import { useQuery } from '@tanstack/react-query';
import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { SearchOutlined } from '@ant-design/icons';
import { Alert, Button, Input, Select, Space, Table, Typography  } from 'antd';
import { DRIVER_NAMES_API_URL, DRIVERS_ARCHIVE_API_URL } from '../api/api';
import BarChart from '../../components/BarChart';
import { pluck } from 'ramda';

const { Title } = Typography

const DriverArchiveList = () => {
  const navigate = useNavigate();
  const [selectedDriver, setSelectedDriver] = useState(null);

  // Fetch specific driver data to display charts
  const fetchChartData = async (selectedDriver) => {
      const response = await fetch(`${DRIVERS_ARCHIVE_API_URL}?driver=${selectedDriver}`);
      return response.json();
  };

  const { data: chartData } = useQuery({
    queryKey: ["driverChartData", selectedDriver],
    queryFn: () => fetchChartData(selectedDriver),
    // Enable only when a user selects a team from the dropdown
    enabled: !!selectedDriver
  });

  // Fetch all driver names to display in the Select menu
  const fetchDriverNames = async () => {
    const nameResponse = await fetch(DRIVER_NAMES_API_URL);
    return nameResponse.json();
  }

  const { data: selectData } = useQuery({
    queryKey: ['driverNamesData'],
    queryFn: fetchDriverNames,
  });

  const fetchData = async () => {
      const response = await fetch(DRIVERS_ARCHIVE_API_URL);
      const json = await response.json();
      return json.map((r) => ({
        key: `${r.year}|${r.driver}`,
        ...r
      }));
    };

  const { data: tableData, error } = useQuery({
    queryKey: ['driverArchiveData'],
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

    const handleOnDriverSelection = (driver) => {
      setSelectedDriver(driver);
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
            title: 'Driver',
            dataIndex: 'driver',
            key: 'driver',
            ...getColumnSearchProps('driver'),
            sorter: (a, b) => a.driver.localeCompare(b.driver),
            sortDirections: ['descend', 'ascend'],
            render: (text) => (
              <Button type="link" onClick={() => handleOnDriverSelection(text)} size='small'>
                {text}
              </Button>
            ),
        },
        {
          title: 'Nationality',
          dataIndex: 'nationality',
          key: 'nationality',
          ...getColumnSearchProps('nationality'),
          sorter: (a, b) => a.nationality.localeCompare(b.nationality),
          sortDirections: ['descend', 'ascend'],
        },
        {
          title: 'Car',
          dataIndex: 'car',
          key: 'car',
          ...getColumnSearchProps('car'),
          sorter: (a, b) => a.car.localeCompare(b.car),
          sortDirections: ['descend', 'ascend'],
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
      navigate('/drivers');
    };

    const clearSelect = () => {
      setSelectedDriver (null);
    }

    return (
        <>
        <Space direction='vertical' style={{width: '20%'}}>
          { selectData &&
              <Select
                showSearch
                value={selectedDriver}
                placeholder="Select Driver Name"
                options={selectData}
                style={{width: '100%'}}
                onChange={handleOnDriverSelection}
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

        <Title level={3}>{selectedDriver} Historical Data</Title>
        { error &&
                <Alert message={`Error: ${error.message}`} type='error'/>
        }
        { !selectedDriver && tableData &&
            <Table
                bordered
                dataSource={tableData}
                columns={columns}
                size='small'
            />
        }
        { selectedDriver && chartData &&
            <BarChart
              xLabel={pluck('year', chartData)}
              data={pluck('points', chartData)}
              datasetLabel="Points"
            />
        }
        </>
    )
}

export default DriverArchiveList
