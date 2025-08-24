from bs4 import BeautifulSoup
from datetime import date
import requests
import pandas as pd

def scrape():
    base_url = "https://www.formula1.com/en/results"

    start_year = 1950
    end_year = date.today().year

    column_headers = ['year', 'position', 'driver', 'nationality', 'car', 'points']
    data = []

    for year in range(start_year, end_year):
        archive_url = f"{base_url}/{year}/drivers"
        print(f"Scraping {archive_url}")

        response = requests.get(archive_url).content
        soup = BeautifulSoup(response, 'html.parser')
        rows = soup.find('tbody').find_all('tr')
        for row in rows:
            archive_data = []
            archive_data.append(year)
            ps = row.find_all('p')

            for p in ps:
                value = p.text

                # Driver and Car name are in anchors
                a = p.a
                if a != None:
                    value = a.get('href').rstrip('/').split('/')[-1].replace('-', ' ').title()

                archive_data.append(value)

            data.append(archive_data)

    df = pd.DataFrame(data, columns=column_headers)
    df.to_csv('drivers_archive.csv', index=False)

if __name__ == "__main__":
    scrape()
