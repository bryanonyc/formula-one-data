#!/usr/bin/env bash
# Create and start project containers, only if containers do not exist
if [[ $(docker ps -a) != *"f1_stats"* ]]; then
  printf "No container exists, attempting full compose.\n\n"
  docker-compose up --build -d
else
  printf "Container is stopped.\nAttempting start...\n\n"
  sh ./docker-start.sh
fi
