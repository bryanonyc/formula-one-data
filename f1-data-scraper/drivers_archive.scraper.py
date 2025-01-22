from bs4 import BeautifulSoup
from datetime import date
import requests
import pandas as pd

base_url = "https://www.formula1.com/en/results"

start_year = 1950
end_year = date.today().year

column_headers = ['Year', 'Pos', 'Driver', 'Nationality', 'Car', 'Pts']
archive_data = []

for year in range(start_year, end_year):
    archive_url = f"{base_url}/{year}/drivers"
    print(f"Scraping {archive_url}")

    response = requests.get(archive_url).content
    soup = BeautifulSoup(response, 'html.parser')
    rows = soup.find('tbody').find_all('tr')
    for row in rows:
        data = []
        data.append(year)
        ps = row.find_all('p')

        for p in ps:
            value = p.text

            # Driver name is in multiple span tags in an anchor
            # Car name is in anchor
            a = p.a
            if a != None and a.find_all('span') != []:
                spans = a.find_all('span')
                value = f"{spans[0].text.strip()} {spans[1].text.strip()}"

            data.append(value)

        archive_data.append(data)

df = pd.DataFrame(archive_data, columns=column_headers)
df.to_csv('drivers_archive.csv', index=False)
