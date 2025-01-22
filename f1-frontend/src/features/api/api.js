
import { isNotNil } from 'ramda';

const BACKEND_URL = isNotNil(process.env.API_ENDPOINT_BASE_URL) ? process.env.API_ENDPOINT_BASE_URL : process.env.REACT_APP_API_ENDPOINT_BASE_URL;

const API_BASE_URL = `${BACKEND_URL}/api/v1`;

export const DRIVERS_API_URL = `${API_BASE_URL}/driver`;

export const DRIVERS_ARCHIVE_API_URL = `${API_BASE_URL}/driver/archive`;

export const DRIVER_NAMES_API_URL = `${API_BASE_URL}/driver/archive/names`;

export const TEAMS_API_URL = `${API_BASE_URL}/team`;

export const TEAMS_ARCHIVE_API_URL = `${API_BASE_URL}/team/archive`;

export const TEAM_NAMES_API_URL = `${API_BASE_URL}/team/archive/names`;
