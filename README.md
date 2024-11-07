Configuración del Backend
Pasos para la Creación y Configuración de la Base de Datos
Ejecutar el archivo de creación de base de datos (archivo 1)

Este archivo contiene la estructura inicial de la base de datos. Ejecuta el archivo desde el cliente SQL para crear las tablas y esquemas necesarios.
Cambiar de base de datos

Conéctate a la base de datos ejecutando el siguiente comando en tu cliente SQL:
sql
Copiar código
\c "JJT_DB"
Ejecutar el segundo archivo de base de datos (archivo 2)

Este archivo contiene configuraciones o datos adicionales que deben agregarse a la base de datos creada. Asegúrate de ejecutarlo en el mismo cliente SQL.
Configuración del Archivo de Propiedades
Copiar y renombrar el archivo de configuración

Copia el archivo application-example.properties y renómbralo como application.properties.
Ingresar credenciales y configuraciones

Edita el archivo application.properties recién creado e ingresa las credenciales de base de datos, las configuraciones de correo electrónico y cualquier otra configuración necesaria para la funcionalidad de la aplicación.