# Use Node.js image
FROM node:22-alpine

# Set working directory
WORKDIR /app

# Install dependencies first (for caching)
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the app
COPY . .

# Expose the port React dev server runs on
EXPOSE 3000

# Start the dev server
CMD ["npm", "start"]
