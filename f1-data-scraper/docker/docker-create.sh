#!/usr/bin/env bash
# Create and start project containers, only if containers do not exist
if [[ $(docker ps -a) != *"f1-scraper"* ]]; then
  printf "No container exist, attempting full compose.\n\n"
  docker-compose up --build -d
  sleep 10
else
  printf "Container is stopped.\nAttempting start...\n\n"
  sh ./docker-start.sh
fi
