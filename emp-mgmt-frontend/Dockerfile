### STAGE 1: Build ###
FROM node:12.7-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm set audit false
RUN npm install
RUN npm install bootstrap@3.3.7 --save
RUN npm install --save-dev @angular-devkit/build-angular@0.901.10
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/angular9-springboot-client /usr/share/nginx/html
