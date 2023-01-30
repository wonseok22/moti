# base image
FROM node:18.13

# set working directory
WORKDIR /var/lib/jenkins/workspace/moti_front


# install and cache app dependencies
COPY package.json ${WORKDIR}/package.json
RUN npm install
COPY . .

RUN npm run build

# start app
CMD ["npm", "run", "serve"]