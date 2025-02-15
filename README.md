# F1 Data

F1 Data is a project to display and search historical Formula One Drivers and Constructors data.

The project is divided into three main components: Backend, Frontend and Data Scraping.

The data scraper is written in Python and exposes an API endpoint to fetch updated data for both current teams and current drivers via a Flask application.  The backend is a Spring boot application which serves up the data from the database and also makes the API call to the Python Flask application to fetch updated data when requested by the user.

You can access the app [here.](https://bryano-f1-data.onrender.com/)

## Features

- **Data Scraping**: A python data scraper to scrape driver and team data from the offical Formula One website and also a Flask application to handle on-demand update requests to fetch the latest stats.
- **Backend**: A Spring Boot application that exposes an API endpoint to serve the scraped data. It also makes the API call to the Flask application whenever an update is requested by the user.
- **Database**: A Postgres database containing the scraped data.
- **Frontend**: A React application leveraing Ant Design and React Query to query the backend to fetch the data and display it to the user.

## Screenshots
<img width="1445" alt="Screenshot 2025-01-23 at 7 58 46 AM" src="https://github.com/user-attachments/assets/0573dd1d-6f18-4b79-9037-ec4044428f60" />

<img width="1438" alt="Screenshot 2025-01-23 at 7 59 28 AM" src="https://github.com/user-attachments/assets/74fcf43b-8e89-483f-ac9a-e43ae5e66924" />

<img width="1440" alt="Screenshot 2025-01-23 at 8 00 25 AM" src="https://github.com/user-attachments/assets/ce859150-1e2b-44f8-ac92-421a70afba9f" />

