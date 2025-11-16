# Integrantes
- Gala Justiniano Carlos Andre
- Chavez Cordova Henry Junior
---------------------------------------------------------------
# SICOFAR 

## REQUERIMIENTO 3 
| Requisito | Cumplimiento |
|----------|--------------|
| Gestionar productos | Crear (solo ADMIN), Listar |
| Controlar stock | Actualizar con `nuevoStock` |
| Frontend simple | Bootstrap 5 + JS puro |
| Seguridad | JWT + Roles (ADMIN / USER) |

---------------------------------------------------------------

## Credenciales de Prueba
| Rol | Usuario | Contraseña |
|-----|---------|------------|
| Farmacéutico | `farmaceutico` | `12345` |
| Administrador | `admin_sicofar` | `12345` |

---------------------------------------------------------------

## Endpoints del API
| Método | Ruta | Descripción | Rol |
|--------|------|-------------|-----|
| `POST` | `/api/auth/login` | Login | Público |
| `GET` | `/api/productos` | Listar productos | USER/ADMIN |
| `POST` | `/api/productos` | Crear producto | ADMIN |
| `PATCH` | `/api/productos/{id}/stock` | Actualizar stock | USER/ADMIN |

---------------------------------------------------------------

## Funcionalidades
- Login con JWT
- Listado de productos con **auto-refresh cada 15 segundos**
- **Crear producto** usando **modal de Bootstrap** (solo ADMIN)
- **Actualizar stock** sobrescribiendo el valor actual
- Interfaz **responsive** con Bootstrap 5
- Alertas de éxito y error

----------------------------------------------------------------

## Tecnologías
- **Backend**: Spring Boot 3, Spring Security, JWT, JPA
- **Frontend**: HTML, JavaScript vanilla, Bootstrap 5
- **Base de datos**: SQL Server (`db_sicofar`)
- **Control de versiones**: Git + GitHub

----------------------------------------------------------------

## Diagrama de Actividad
![Diagrama de Actividad](diagrama-actividad-sicofar.png)

----------------------------------------------------------------

## Ejecución
1. Ejecutar `SICOFARApplication.java`
2. Abrir en el navegador: [http://localhost:8080/login.html](http://localhost:8080/login.html)
3. Iniciar sesión con las credenciales de prueba
