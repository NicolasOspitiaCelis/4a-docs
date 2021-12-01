const authResolver = require('./auth_resolver');
const lodash = require('lodash');
const turnsResolver = require('./turnos_resolver');
const resolvers = lodash.merge(turnsResolver, authResolver);
module.exports = resolvers;