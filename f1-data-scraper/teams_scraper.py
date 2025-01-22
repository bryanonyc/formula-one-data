from bs4 import BeautifulSoup
import requests
import pandas as pd

base_url = "https://www.formula1.com"
all_teams_url = f"{base_url}/en/teams"

response = requests.get(all_teams_url).content
soup = BeautifulSoup(response, 'html.parser')
anchors = soup.find_all('a', class_='focus-visible:outline-0')

teams_data = []
column_headers = []

for a in anchors:
    href = a.get('href')
    print(f"Scraping {href}")

    team_response = requests.get(f"{base_url}{href}").content
    soup = BeautifulSoup(team_response, 'html.parser')

    if len(column_headers) == 0:
        dts = soup.find_all('dt', class_='f1-heading')
        for dt in dts:
            column_headers.append(dt.text)

    team_data = []
    dds = soup.find_all('dd', class_='f1-text')
    for dd in dds:
        team_data.append(dd.text)

    teams_data.append(team_data)

df = pd.DataFrame(teams_data, columns=column_headers)
df.to_csv('team_stats.csv', index=False)
