# Use a Node.js image
FROM node:18-alpine

# Set the working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the entire project
COPY . .

# Start the frontend
CMD ["npm", "run", "serve"]

# Expose the frontend port
EXPOSE 8080