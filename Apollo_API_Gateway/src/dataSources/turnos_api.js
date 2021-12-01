const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class TurnsAPI extends RESTDataSource {
    constructor() {
        super();
        this.baseURL = serverConfig.turnos_api_url;
    }

    async createTurn(turn){
        turn = new Object(JSON.parse(JSON.stringify(turn)));
        return await this.post('/turn', turn);
    }

    async turnList(){
        return await this.get('/turnsList');
    }
}

module.exports = TurnsAPI;