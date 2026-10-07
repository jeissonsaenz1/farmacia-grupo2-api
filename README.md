# Farmacia Grupo 2 API

API REST desarrollada con Spring Boot para la gestión de diferentes procesos de una farmacia.

El proyecto permite administrar medicamentos, laboratorios, inventarios, fórmulas, ventas y detalles de venta mediante diferentes endpoints REST.

## Tecnologías utilizadas

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* OpenAPI
* Scalar

## Funcionalidades

El sistema permite:

* Registrar, consultar, actualizar y eliminar medicamentos.
* Registrar, consultar, actualizar y eliminar laboratorios.
* Gestionar el inventario de los medicamentos.
* Registrar y administrar fórmulas.
* Registrar y administrar ventas.
* Registrar y administrar detalles de venta.
* Buscar medicamentos por nombre.
* Buscar medicamentos por laboratorio.
* Buscar medicamentos por rango de precio.
* Consultar el inventario asociado a un medicamento.
* Buscar ventas por estado.
* Validar algunas reglas de negocio relacionadas con medicamentos e inventario.

## Modelo de datos

El proyecto cuenta con las siguientes entidades principales:

* **Laboratorio:** almacena la información de los laboratorios y su dirección.
* **Medicamento:** contiene el nombre, precio y laboratorio asociado.
* **Inventario:** administra el stock, lote y fecha de vencimiento de un medicamento.
* **Fórmula:** permite asociar varios medicamentos a una fórmula.
* **Venta:** registra el total y estado de una venta.
* **DetalleVenta:** relaciona una venta con los medicamentos incluidos y registra la cantidad y subtotal.

### Relaciones principales

* Un **Laboratorio** puede tener varios **Medicamentos**.
* Un **Medicamento** pertenece a un **Laboratorio**.
* Un **Medicamento** tiene un **Inventario**.
* Una **Fórmula** puede tener varios **Medicamentos**.
* Un **Medicamento** puede pertenecer a varias **Fórmulas**.
* Una **Venta** puede tener varios **Detalles de Venta**.
* Cada **Detalle de Venta** está asociado a una **Venta** y a un **Medicamento**.

## Reglas de negocio

El proyecto implementa las siguientes validaciones:

1. El precio de un medicamento debe ser mayor que cero.
2. El stock del inventario no puede ser negativo.
3. La fecha de vencimiento del inventario es obligatoria.
4. No se puede registrar un medicamento con una fecha de vencimiento anterior a la fecha actual.

## Endpoints

La API utiliza el prefijo `/api`.

### Medicamentos

| Método | Endpoint                                                     | Descripción                             |
| ------ | ------------------------------------------------------------ | --------------------------------------- |
| POST   | `/api/medicamentos`                                          | Crear medicamento                       |
| GET    | `/api/medicamentos`                                          | Listar medicamentos                     |
| GET    | `/api/medicamentos/{id}`                                     | Buscar medicamento por ID               |
| GET    | `/api/medicamentos/buscar?nombre=`                           | Buscar medicamentos por nombre          |
| GET    | `/api/medicamentos/laboratorio/{laboratorioId}`              | Buscar medicamentos por laboratorio     |
| GET    | `/api/medicamentos/rango-precio?precioMinimo=&precioMaximo=` | Buscar medicamentos por rango de precio |
| PUT    | `/api/medicamentos/{id}`                                     | Actualizar medicamento                  |
| DELETE | `/api/medicamentos/{id}`                                     | Eliminar medicamento                    |

### Laboratorios

| Método | Endpoint                 | Descripción               |
| ------ | ------------------------ | ------------------------- |
| POST   | `/api/laboratorios`      | Crear laboratorio         |
| GET    | `/api/laboratorios`      | Listar laboratorios       |
| GET    | `/api/laboratorios/{id}` | Buscar laboratorio por ID |
| PUT    | `/api/laboratorios/{id}` | Actualizar laboratorio    |
| DELETE | `/api/laboratorios/{id}` | Eliminar laboratorio      |

### Inventarios

| Método | Endpoint                                       | Descripción                       |
| ------ | ---------------------------------------------- | --------------------------------- |
| POST   | `/api/inventarios`                             | Crear inventario                  |
| GET    | `/api/inventarios`                             | Listar inventarios                |
| GET    | `/api/inventarios/{id}`                        | Buscar inventario por ID          |
| GET    | `/api/inventarios/medicamento/{medicamentoId}` | Buscar inventario por medicamento |
| PUT    | `/api/inventarios/{id}`                        | Actualizar inventario             |
| DELETE | `/api/inventarios/{id}`                        | Eliminar inventario               |

### Fórmulas

| Método | Endpoint             | Descripción           |
| ------ | -------------------- | --------------------- |
| POST   | `/api/formulas`      | Crear fórmula         |
| GET    | `/api/formulas`      | Listar fórmulas       |
| GET    | `/api/formulas/{id}` | Buscar fórmula por ID |
| PUT    | `/api/formulas/{id}` | Actualizar fórmula    |
| DELETE | `/api/formulas/{id}` | Eliminar fórmula      |

### Ventas

| Método | Endpoint                     | Descripción              |
| ------ | ---------------------------- | ------------------------ |
| POST   | `/api/ventas`                | Crear venta              |
| GET    | `/api/ventas`                | Listar ventas            |
| GET    | `/api/ventas/{id}`           | Buscar venta por ID      |
| GET    | `/api/ventas/estado?estado=` | Buscar ventas por estado |
| PUT    | `/api/ventas/{id}`           | Actualizar venta         |
| DELETE | `/api/ventas/{id}`           | Eliminar venta           |

### Detalles de venta

| Método | Endpoint                   | Descripción                 |
| ------ | -------------------------- | --------------------------- |
| POST   | `/api/detalles-venta`      | Crear detalle de venta      |
| GET    | `/api/detalles-venta`      | Listar detalles de venta    |
| GET    | `/api/detalles-venta/{id}` | Buscar detalle por ID       |
| PUT    | `/api/detalles-venta/{id}` | Actualizar detalle de venta |
| DELETE | `/api/detalles-venta/{id}` | Eliminar detalle de venta   |

## Estructura del proyecto

El proyecto está organizado siguiendo una estructura por capas:

```text
src/
└── main/
    └── java/
        └── com/
            └── cesde/
                └── farmaciagrupo2/
                    ├── config/
                    ├── controller/
                    ├── exception/
                    ├── model/
                    │   ├── base/
                    │   ├── embeddable/
                    │   ├── entity/
                    │   └── enums/
                    ├── repository/
                    ├── service/
                    └── FarmaciaGrupo2Application.java
```

### Principales capas

**Controller:** recibe y responde las peticiones HTTP de la API.

**Service:** contiene la lógica de los procesos y las reglas de negocio.

**Repository:** permite realizar las operaciones con la base de datos mediante Spring Data JPA.

**Entity:** representa las entidades que se almacenan en la base de datos.

## Configuración

El proyecto utiliza PostgreSQL como base de datos.

Las credenciales de conexión se manejan mediante variables de entorno:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

No se deben subir credenciales reales al repositorio.

## Ejecución del proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/jeissonsaenz1/farmacia-grupo2-api.git
```

### 2. Entrar al proyecto

```bash
cd farmacia-grupo2-api
```

### 3. Configurar las variables de entorno

Configurar:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

con los datos correspondientes a la base de datos PostgreSQL.

### 4. Ejecutar el proyecto

Con Maven:

```bash
mvn spring-boot:run
```

También puede ejecutarse desde el IDE utilizando la clase principal:

```text
FarmaciaGrupo2Application
```

## Datos iniciales

El proyecto cuenta con una configuración de carga de datos iniciales mediante `DataSeederConfig`.

Esta configuración permite cargar datos iniciales relacionados con:

* Laboratorios
* Medicamentos
* Inventarios
* Fórmulas
* Ventas
* Detalles de venta

Los datos se cargan evitando duplicarlos cuando ya existen registros.

## Documentación de la API

El proyecto cuenta con documentación mediante OpenAPI y una interfaz de Scalar para consultar y probar los endpoints disponibles.

La URL de documentación depende de la configuración utilizada en el proyecto.

## Integrantes

**Grupo 4 en SMARTCLASS (inicialmente Grupo 2) — CESDE**

1. Edier Alejandro Ospina Arbelaez
2. Jahira Janeth Rojas Lobo
3. Jeisson David Betancur Saenz

Proyecto Integrador desarrollado como parte del programa de formación en Desarrollo de Software.
