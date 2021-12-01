const turnsResolver = {
    Query: {
        turnList: async(_, { username }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken)).username;
            if (username == usernameToken)
                return dataSources.TurnsAPI.turnList();
            else
                return null
        }

    },

    Mutation: {
        createTurn: async(_, { turn }, { dataSources }) => {
            return await dataSources.TurnsAPI.createTurn(turn);
        }

    }
};

module.exports = turnsResolver;