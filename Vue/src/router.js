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
    },
    {
        path: "/inicio",
        name: "inicio",
        component: Inicio,
    },
    {
        path: "/inicio/resumen",
        name: "resumen",
        component: Resumen,
    },
    {
        path: "/user/iniciar",
        name: "iniciar",
        component: Iniciar,
    },
    {
        path: "/user/administrar",
        name: "administrar",
        component: Administrar,
    },
    {
        path: "/user/administrar/reporte",
        name: "reporte",
        component: Reporte,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;