import {
    createRouter,
    createWebHistory
} from "vue-router";
import App from "./App.vue";
import Inicio from "./components/Inicio.vue";
import Administrar from "./components/Administrar.vue";
import Iniciar from "./components/Iniciar.vue";
import Registro from "./components/Registro.vue";
import reporte from "./components/Reporte.vue";

const routes = [{
        path: "/",
        name: "root",
        component: App,
    },
    {
        path: "/user/iniciar",
        name: "iniciar",
        component: Iniciar,
    },
    {
        path: "/inicio",
        name: "inicio",
        component: Inicio,
    },
    {
        path: "/user/registro",
        name: "registro",
        component: Registro,
    },
    {
        path: "/user/administrar",
        name: "administrar",
        component: Administrar,
    },
    {
        path: "/user/administrar/reporte",
        name: "reporte",
        component: reporte,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;