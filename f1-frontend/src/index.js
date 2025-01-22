import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { App as AntdApp } from "antd"
// import '@ant-design/v5-patch-for-react-19';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient();

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <AntdApp>
        <App />
      </AntdApp>
    </QueryClientProvider>
  </React.StrictMode>
);
