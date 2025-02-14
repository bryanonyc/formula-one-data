from bs4 import BeautifulSoup
import requests
import pandas as pd

def scrape():
    base_url = "https://www.formula1.com"
    all_drivers_url = f"{base_url}/en/drivers"

    response = requests.get(all_drivers_url).content
    soup = BeautifulSoup(response, 'html.parser')
    anchors = soup.find_all('a', class_='focus-visible:outline-0')

    drivers_data = []
    data_columns = []

    for a in anchors:
        href = a.get('href')
        print(f"Scraping {href}")

        driver_response = requests.get(f"{base_url}{href}").content
        soup = BeautifulSoup(driver_response, 'html.parser')
        driver_name = soup.find('h1', class_='f1-heading').text

        if len(data_columns) == 0:
            data_columns.append("Driver")
            dts = soup.find_all('dt', class_='f1-heading')
            for dt in dts:
                data_columns.append(dt.text)

        driver_data = []
        driver_data.append(driver_name)

        dds = soup.find_all('dd', class_='f1-text')
        for dd in dds:
            driver_data.append(dd.text)

        drivers_data.append(driver_data)

    # Construct a pandas DataFrame
    data_frames = pd.DataFrame(drivers_data, columns=data_columns)

    # Output Data Frame to CSV without the deffault index
    data_frames.to_csv('driver_stats.csv', index=False)
