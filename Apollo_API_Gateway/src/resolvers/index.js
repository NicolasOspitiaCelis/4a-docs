const turnosResolver = require('./turnos_resolver');
const authResolver = require('./auth_resolver');
const lodash = require('lodash');
const resolvers = lodash.merge(turnosResolver, authResolver);
module.exports = resolvers;