1. Set up your enviroment: 
    CypressJS: **npm init -y** && **npm install cypress --save-dev**
    unit tests: **npm install --save-dev jest jest-environment-jsdom @babel/core @babel/preset-env @babel/preset-react babel-jest dotenv @testing-library/react-hooks** 
    Browserstack: **npm install -D browserstack-cypress-cli** && **npx browserstack-cypress init**
2. Build the docker (if needed): **docker-compose build**
3. Run: **docker-compose up**
4. Run tests: 
    CypressJS: **npm run cypress:open** 
    unit tests: **npm test** 
    api module tests: **go test ./...** 
    BrowserStack tests: **browserstack-cypress start --sync** && **browserstack-cypress run**