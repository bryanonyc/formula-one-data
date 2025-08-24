# Use the official Python image
FROM python:3.13-slim

# Upgrade system packages to fix vulnerabilities
RUN apt-get update && apt-get upgrade -y && apt-get clean

# Set working directory
WORKDIR /app

# Copy requirements first to leverage Docker cache
COPY requirements.txt .

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the app
COPY . .

# Create writable directory for output
RUN mkdir -p /app/data

# Ensure the data directory is writable
RUN chmod -R 777 /app/data

# Run the app
CMD ["python3", "main.py"]
