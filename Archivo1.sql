REATE DATABASE "JJT_DB"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
-- Crear la extensión para UUID si es necesario
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";