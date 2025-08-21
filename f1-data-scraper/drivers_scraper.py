from bs4 import BeautifulSoup
import requests
import pandas as pd

def scrape():
    base_url = "https://www.formula1.com"
    all_drivers_url = f"{base_url}/en/drivers"

    response = requests.get(all_drivers_url).content
    soup = BeautifulSoup(response, 'html.parser')
    anchors = soup.find_all(attrs={'data-f1rd-a7s-click': 'driver_card_click'})

    column_headers = ['driver', 'team', 'country', 'podiums', 'points',
                      'grands_prix_entered', 'world_championships',
                      'highest_race_finish', 'highest_grid_position',
                      'date_of_birth', 'place_of_birth']
    data = []

    for a in anchors:
        href = a.get('href')

        if not href:
            continue

        print(f"Scraping {href}")

        driver_response = requests.get(f"{base_url}{href}").content
        soup = BeautifulSoup(driver_response, 'html.parser')

        bio = soup.find('div', id='biography')

        driver_data = []
        driver_data.append(_get_driver_name(href))
        driver_data.append(_get_team_name(soup))
        driver_data.append(_get_country_name(soup))
        driver_data.append(_get_podiums(soup))
        driver_data.append(_get_points(soup))
        driver_data.append(_get_grands_prix_entered(soup))
        driver_data.append(_get_world_championships(soup))
        driver_data.append(_get_highest_race_finish(soup))
        driver_data.append(_get_highest_grid_position(soup))
        driver_data.append(_get_dob(bio))
        driver_data.append(_get_place_of_birth(bio))

        data.append(driver_data)

    # Construct a pandas DataFrame
    data_frames = pd.DataFrame(data, columns=column_headers)

    # Output Data Frame to CSV without the deffault index
    data_frames.to_csv('driver_stats.csv', index=False)

def _get_driver_name(href):
    return href.rstrip('/').split('/')[-1].replace('-', ' ').title()

def _get_team_name(soup):
    return soup.find('h1').nextSibling.findAllNext('p')[1].text

def _get_country_name(soup):
    return soup.find('h1').nextSibling.findAllNext('p')[0].text

def _get_podiums(soup):
    return soup.find('dt', string='Podiums').find_next_sibling('dd').text

def _get_points(soup):
    return soup.find('dt', string='Career Points').find_next_sibling('dd').text

def _get_grands_prix_entered(soup):
    return soup.find('dt', string='Grand Prix Entered').find_next_sibling('dd').text

def _get_world_championships(soup):
    return soup.find('dt', string='World Championships').find_next_sibling('dd').text

def _get_highest_race_finish(soup):
    return soup.find('dt', string='Highest Race Finish').find_next_sibling('dd').text

def _get_highest_grid_position(soup):
    return soup.find('dt', string='Highest Grid Position').find_next_sibling('dd').text

def _get_dob(bio):
    return bio.find('dt', string='Date of Birth').find_next_sibling('dd').text

def _get_place_of_birth(bio):
    return bio.find('dt', string='Place of Birth').find_next_sibling('dd').text

if __name__ == "__main__":
    scrape()
