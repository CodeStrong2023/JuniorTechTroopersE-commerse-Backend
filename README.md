# TrooperStay

TrooperStay es una plataforma de reservas de alojamiento que permite a los usuarios encontrar y alquilar departamentos, habitaciones de hotel y cabañas de manera sencilla. Su backend, desarrollado en Java con Spring Boot, garantiza escalabilidad y seguridad en los servicios de búsqueda y reservas. Con PostgreSQL como base de datos, la plataforma gestiona eficientemente los datos de usuarios, alojamientos e imágenes, asegurando integridad y consistencia para una experiencia fluida y confiable.

## 🛠️ Tecnologías Utilizadas

### Backend

- <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
- <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot" />
- <img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
- <img src="https://img.shields.io/badge/JJWT-4A4A4A?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JJWT" />
- <img src="https://img.shields.io/badge/Lombok-4A4A4A?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok" />
- <img src="https://img.shields.io/badge/JBCrypt-4A4A4A?style=for-the-badge&logo=java&logoColor=white" alt="JBCrypt" />
- <img src="https://img.shields.io/badge/Mail-4A4A4A?style=for-the-badge&logo=gmail&logoColor=white" alt="Mail" />
  

## 🔧 Configuración del Backend

Para clonar este proyecto y configurarlo en tu entorno local, sigue estos pasos:

1. **Clona el repositorio**:

    ```bash
    git clone https://github.com/tuusuario/trooperstays.git
    ```

2. **Navega al directorio del proyecto**:

    ```bash
    cd trooperstays
    ```

3. **Ejecuta el archivo de creación de base de datos (archivo 1)** en pgAdmin4 o cualquier IDE de PostgreSQL.

    Este archivo contiene la estructura inicial de la base de datos. Ejecuta el archivo desde el cliente SQL para crear las tablas y esquemas necesarios.

4. **Cambia de base de datos**:

    Conéctate a la base de datos ejecutando el siguiente comando en tu cliente SQL:
    ```sql
    \c "JJT_DB"
    ```

5. **Ejecuta el segundo archivo de base de datos (archivo 2)**:

    Este archivo contiene configuraciones o datos adicionales que deben agregarse a la base de datos creada. Asegúrate de ejecutarlo en el mismo cliente SQL.

6. **Abre el proyecto backend** con el IDE pertinente.

7. **Copia y renombra el archivo de configuración**:

    - Copia el archivo `application-example.properties` y renómbralo como `application.properties`.
    - Ingresa las credenciales y configuraciones necesarias.

8. **Edita `application.properties`**:

    Ingresa las credenciales de la base de datos, las configuraciones de correo electrónico y cualquier otra configuración necesaria para el correcto funcionamiento de la aplicación.

9. **Ejecuta el archivo `pom.xml`** para instalar las dependencias.

   Esto permitirá descargar e instalar todas las dependencias definidas en el proyecto.
