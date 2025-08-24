from bs4 import BeautifulSoup
import requests
import pandas as pd

def scrape():
    base_url = "https://www.formula1.com"
    all_teams_url = f"{base_url}/en/teams"

    response = requests.get(all_teams_url).content
    soup = BeautifulSoup(response, 'html.parser')
    anchors = soup.find_all(attrs={'data-f1rd-a7s-click': 'team_card_click'})

    column_headers = ['full_team_name', 'base', 'team_chief', 'technical_chief',
                      'chassis', 'power_unit', 'first_team_entry',
                      'world_championships', 'highest_race_finish',
                      'pole_positions']
    data = []

    for a in anchors:
        href = a.get('href')

        if not href:
            continue

        print(f"Scraping {href}")

        team_response = requests.get(f"{base_url}{href}").content
        soup = BeautifulSoup(team_response, 'html.parser')

        team_data = []
        team_data.append(_get_team_name(soup))
        team_data.append(_get_base(soup))
        team_data.append(_get_team_chief(soup))
        team_data.append(_get_technical_chief(soup))
        team_data.append(_get_chassis(soup))
        team_data.append(_get_power_unit(soup))
        team_data.append(_get_first_team_entry(soup))
        team_data.append(_get_world_championships(soup))
        team_data.append(_get_highest_race_finish(soup))
        team_data.append(_get_pole_positions(soup))

        data.append(team_data)

    data_frames = pd.DataFrame(data, columns=column_headers)
    data_frames.to_csv('data/team_stats.csv', index=False)

def _get_team_name(soup):
    return soup.find('dt', string='Full Team Name').find_next_sibling('dd').text

def _get_base(soup):
    return soup.find('dt', string='Base').find_next_sibling('dd').text

def _get_team_chief(soup):
    return soup.find('dt', string='Team Chief').find_next_sibling('dd').text

def _get_technical_chief(soup):
    return soup.find('dt', string='Technical Chief').find_next_sibling('dd').text

def _get_chassis(soup):
    return soup.find('dt', string='Chassis').find_next_sibling('dd').text

def _get_power_unit(soup):
    return soup.find('dt', string='Power Unit').find_next_sibling('dd').text

def _get_first_team_entry(soup):
    return soup.find('dt', string='First Team Entry').find_next_sibling('dd').text

def _get_world_championships(soup):
    return soup.find('dt', string='World Championships').find_next_sibling('dd').text

def _get_highest_race_finish(soup):
    return soup.find('dt', string='Highest Race Finish').find_next_sibling('dd').text

def _get_pole_positions(soup):
    return soup.find('dt', string='Pole Positions').find_next_sibling('dd').text

if __name__ == "__main__":
    scrape()
