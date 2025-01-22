import { useTitle } from './hooks/useTitle';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AppLayout from './components/AppLayout';
import DriverHome from './features/drivers/DriverHome';
import DriverArchiveHome from './features/drivers/DriverArchiveHome';
import TeamHome from './features/teams/TeamHome';
import TeamArchiveHome from './features/teams/TeamArchiveHome';
import Welcome from './components/Welcome';
import { NOT_FOUND_404 } from './components/Results';
import RequireEnv from './components/RequireEnv';

function App() {
  useTitle('F1 Data');

  return (
    <BrowserRouter>
      <Routes>
        <Route index
          element={
          <AppLayout>
            <Welcome />
          </AppLayout>
          }
        />

        <Route element={<RequireEnv />}>
          <Route path='drivers'>
            <Route index
              element={
                <AppLayout>
                  <DriverHome />
                </AppLayout>
              }
            />
            <Route path="archive"
                element={
                    <AppLayout>
                        <DriverArchiveHome />
                    </AppLayout>
                }
            />
          </Route>

          <Route path='teams'>
            <Route index
              element={
                <AppLayout>
                  <TeamHome />
                </AppLayout>
              }
            />

            <Route path="archive">
              <Route index
                element={
                    <AppLayout>
                        <TeamArchiveHome />
                    </AppLayout>
                }
              />
            </Route>
          </Route>
        </Route>

        <Route
            path="*"
            element={<NOT_FOUND_404 />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
