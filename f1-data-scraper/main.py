import pandas as pd
import drivers_archive_scraper
import drivers_scraper
import teams_archive_scraper
import teams_scraper
from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/")
def home():
    return "Welcome"

@app.route("/api/drivers/current")
def current_drivers():
    drivers_scraper.scrape()

    df = pd.read_csv("data/driver_stats.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)

@app.route("/api/drivers/archive")
def archive_drivers():
    drivers_archive_scraper.scrape()

    df = pd.read_csv("data/drivers_archive.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)

@app.route("/api/teams/current")
def current_teams():
    teams_scraper.scrape()

    df = pd.read_csv("data/team_stats.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)

@app.route("/api/teams/archive")
def archive_teams():
    teams_archive_scraper.scrape()

    df = pd.read_csv("data/teams_archive.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
