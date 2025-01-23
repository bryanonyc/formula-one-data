import React from 'react'
import { Bar } from 'react-chartjs-2'
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title as ChartJSTitle,
    Tooltip,
    Legend,
} from "chart.js";

const chartJSOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
    },
  };

  ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    ChartJSTitle,
    Tooltip,
    Legend
  );

const BarChart = ({
    xLabel,
    data,
    datasetLabel
}) => {
    const chartData = {
        labels: xLabel,
        datasets: [{
            label: `${datasetLabel}`,
            data,
            backgroundColor: "#2A746E",
        }],
    };
  return (
    <div className='center-content'>
      <div className='chart-container'>
        <Bar data={chartData} options={chartJSOptions} />
      </div>
    </div>
  )
}

export default BarChart
