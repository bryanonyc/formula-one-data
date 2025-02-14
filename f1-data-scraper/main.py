import pandas as pd
import drivers_scraper
import teams_scraper
from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/")
def home():
    return "Welcome"

@app.route("/api/drivers/current")
def current_drivers():
    drivers_scraper.scrape()

    df = pd.read_csv("driver_stats.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)

@app.route("/api/teams/current")
def current_teams():
    teams_scraper.scrape()

    df = pd.read_csv("team_stats.csv")

    data = df.to_dict(orient="records")

    # Return JSON response using jsonify
    return jsonify(data)


if __name__ == "__main__":
    app.run()
