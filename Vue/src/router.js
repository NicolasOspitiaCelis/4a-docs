import {
    createRouter,
    createWebHistory
} from "vue-router";
import App from "./App.vue";
import Inicio from "./components/Inicio.vue";
import Administrar from "./components/Administrar.vue";
import Iniciar from "./components/Iniciar.vue";
import Reporte from "./components/Reporte.vue";
import Resumen from "./components/Resumen.vue";

const routes = [{
        path: "/",
        name: "root",
        component: App,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/inicio",
        name: "inicio",
        component: Inicio,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/inicio/resumen",
        name: "resumen",
        component: Resumen,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/user/iniciar",
        name: "iniciar",
        component: Iniciar,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/user/administrar",
        name: "administrar",
        component: Administrar,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/user/administrar/reporte",
        name: "reporte",
        component: Reporte,
        meta: {
            requiresAuth: true
        }
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

/*const apolloClient = new ApolloClient({
    link: createHttpLink({
        uri: 'https://car-api-gateway.herokuapp.com/'
    }),
    cache: new InMemoryCache()
})

async function isAuth() {
    if (localStorage.getItem("token_access") === null ||
        localStorage.getItem("token_refresh") === null) {
        return false;
    }
    try {
        var result = await apolloClient.mutate({
            mutation: gql `
                mutation ($refresh: String!) {
                    refreshToken(refresh: $refresh) {
                        access
                    }
                }`,
            variables: {
                refresh: localStorage.getItem("token_refresh"),
            },
        })
        localStorage.setItem("token_access", result.data.refreshToken.access);
        return true;
    } catch {
        localStorage.clear();
        alert("Su sesión expiró, por favor vuelva a iniciar sesión");
        return false;
    }
}

router.beforeEach(async (to, from) => {
    var is_auth = await isAuth();
    if (is_auth == to.meta.requiresAuth) return true
    if (is_auth) return {
        name: "home"
    };
    return {
        name: "logIn"
    };
})*/


export default router;