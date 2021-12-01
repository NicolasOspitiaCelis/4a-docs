//Se llama al typedef (esquema) de cada submodulo
const authTypeDefs = require('./auth_type_defs');
const turnsTypeDefs = require('./turnos_type_defs');
//Se unen
const schemasArrays = [authTypeDefs, turnsTypeDefs];
//Se exportan
module.exports = schemasArrays;