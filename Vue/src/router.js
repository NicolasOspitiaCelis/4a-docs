import {
    createRouter,
    createWebHistory
} from "vue-router";
import App from "./App.vue";
import Inicio from "./components/Inicio.vue";
import Administrar from "./components/Administrar.vue";
import Iniciar from "./components/Iniciar.vue";
import Registro from "./components/Registro.vue";
import Proveedores from "./components/Proveedor/GestionProveedores.vue";
import ingresarProveedor from "./components/Proveedor/ingresarProveedor.vue";
import actualizarProveedor from "./components/Proveedor/actualizarProveedor.vue";
import reporteProveedor from "./components/Proveedor/reporteProveedor.vue";
import eliminarProveedor from "./components/Proveedor/eliminarProveedor.vue";
import Productos from "./components/Producto/GestionProductos.vue";
import ingresarProducto from "./components/Producto/ingresarProducto.vue";
import actualizarProducto from "./components/Producto/actualizarProducto.vue";
import listaProducto from "./components/Producto/listaProducto.vue";
import eliminarProducto from "./components/Producto/eliminarProducto.vue";
import Inventario from "./components/Inventario/GestionInventario.vue";
import ingresarInventario from "./components/Inventario/ingresarInventario.vue";
import actualizarInventario from "./components/Inventario/actualizarInventario.vue";
import listaInventario from "./components/Inventario/listaInventario.vue";
import eliminarInventario from "./components/Inventario/eliminarInventario.vue";
import Empleados from "./components/Empleado/GestionEmpleados.vue";
import ingresarEmpleado from "./components/Empleado/ingresarEmpleado.vue";
import actualizarEmpleado from "./components/Empleado/actualizarEmpleado.vue";
import listaEmpleado from "./components/Empleado/listaEmpleado.vue";
import eliminarEmpleado from "./components/Empleado/eliminarEmpleado.vue";

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
        path: "/user/administrar/g_proveedores",
        name: "g_proveedores",
        component: Proveedores,
    },
    {
        path: "/user/administrar/g_proveedores/ingresar",
        name: "ingresarproveedor",
        component: ingresarProveedor,
    },
    {
        path: "/user/administrar/g_proveedores/actualizar",
        name: "actualizarproveedor",
        component: actualizarProveedor,
    },
    {
        path: "/user/administrar/g_proveedores/reporte",
        name: "reporteproveedor",
        component: reporteProveedor,
    },
    {
        path: "/user/administrar/g_proveedores/eliminar",
        name: "eliminarproveedor",
        component: eliminarProveedor,
    },
    {
        path: "/user/administrar/g_productos",
        name: "g_productos",
        component: Productos,
    },
    {
        path: "/user/administrar/g_productos/ingresar",
        name: "ingresarProducto",
        component: ingresarProducto,
    },
    {
        path: "/user/administrar/g_productos/actualizar",
        name: "actualizarProducto",
        component: actualizarProducto,
    },
    {
        path: "/user/administrar/g_productos/reporte",
        name: "listaProducto",
        component: listaProducto,
    },
    {
        path: "/user/administrar/g_productos/eliminar",
        name: "eliminarProducto",
        component: eliminarProducto,
    },
    {
        path: "/user/administrar/g_inventario",
        name: "Inventario",
        component: Inventario,
    },
    {
        path: "/user/administrar/g_inventario/ingresar",
        name: "ingresarInventario",
        component: ingresarInventario,
    },
    {
        path: "/user/administrar/g_inventario/actualizar",
        name: "actualizarInventario",
        component: actualizarInventario,
    },
    {
        path: "/user/administrar/g_inventario/reporte",
        name: "listaInventario",
        component: listaInventario,
    },
    {
        path: "/user/administrar/g_inventario/eliminar",
        name: "eliminarInventario",
        component: eliminarInventario,
    },
    {
        path: "/user/administrar/g_empleados",
        name: "g_empleados",
        component: Empleados,
    },
    {
        path: "/user/administrar/g_empleados/ingresar",
        name: "ingresarempleado",
        component: ingresarEmpleado,
    },
    {
        path: "/user/administrar/g_empleados/actualizar",
        name: "actualizarempleado",
        component: actualizarEmpleado,
    },
    {
        path: "/user/administrar/g_empleados/reporte",
        name: "listaempleado",
        component: listaEmpleado,
    },
    {
        path: "/user/administrar/g_empleados/eliminar",
        name: "eliminarempleado",
        component: eliminarEmpleado,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;