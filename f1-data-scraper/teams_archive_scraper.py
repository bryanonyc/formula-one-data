from datetime import date
from bs4 import BeautifulSoup
import requests
import pandas as pd

base_url = "https://www.formula1.com/en/results"

start_year = 1958
end_year = date.today().year

column_headers = ['year', 'position', 'team', 'points']
archive_data = []

for year in range(start_year, end_year):
    archive_url = f"{base_url}/{year}/team"
    print(f"Scraping {base_url}/{year}/team")

    response = requests.get(archive_url).content
    soup = BeautifulSoup(response, 'html.parser')

    rows = soup.find('tbody').find_all('tr')
    for row in rows:
        data = []
        data.append(year)
        ps = row.find_all('p')
        for p in ps:
            data.append(p.text)
        archive_data.append(data)

df = pd.DataFrame(archive_data, columns=column_headers)
df.to_csv('teams_archive.csv', index=False)
