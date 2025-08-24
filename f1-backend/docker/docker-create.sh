#!/usr/bin/env bash
# Create and start project containers, only if containers do not exist
if [[ $(docker ps) != *"f1_stats_postgres_db"* ]]; then
  if [[ $(docker volume ls) != *"f1_stats_pgvolume"* ]]; then
    printf "No containers or persistent volumes exist, attempting full compose.\n\n"
    docker-compose up -d
    sleep 10
    printf "Creating initial f1_stats database\n"
    docker exec -i f1_stats_postgres_db psql -U postgres -c "CREATE DATABASE f1_stats;"
  else
    printf "Containers are stopped but database and persistent volumes already exist.\nAttempting start...\n\n"
    sh ./docker-start.sh
  fi
else
  printf "Existing containers are running and appear to be in a good state.\n"
fi
