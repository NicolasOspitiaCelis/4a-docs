const { gql } = require('apollo-server');

const turnsTypeDefs = gql `
    type Turn {
        id: String!
        entity: String!
        document: Int!
        names: String!
        lastName: String!
        phone: String!
        date: String!
        turn: Int!
    }

    input TurnInput {
        entity: String!
        document: Int!
        names: String!
        lastName: String!
        phone: String!
    }

    extend type Query {
        turnList(username: String!): [Turn]
    }

    extend type Mutation {
        createTurn(turn: TurnInput!): Turn
    }
`;

module.exports = turnsTypeDefs;