//Se llama al typedef (esquema) de cada submodulo
const turnosTypeDefs = require('./turnos_type_defs');
const authTypeDefs = require('./auth_type_defs');
//Se unen
const schemasArrays = [authTypeDefs, turnosTypeDefs];
//Se exportan
module.exports = schemasArrays;