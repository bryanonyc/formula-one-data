# F1 Data

F1 Data is a project to display and search historical Formula One Drivers and Constructors data.

The project is divided into three main components: Backend, Frontend and Data Scraping.

You can access the app [here.](https://bryano-f1-data.onrender.com/)

## Features

- **Data Scraping**: A python data scraper to scrape driver and team data from the offical Formula One website.
- **Backend**: A Spring Boot application that exposes an API endpoint to serve the scraped data.
- **Database**: A Postgres database containing the scraped data.
- **Frontend**: A React application leveraing Ant Design and React Query to query the backend to fetch the data and display it to the user.
