FROM node:14
WORKDIR /app
COPY . .
RUN npm install && npm run build
CMD ["npx", "vite", "preview", "--host"]
