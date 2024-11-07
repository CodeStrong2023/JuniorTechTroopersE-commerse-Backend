CREATE TABLE users (
  id serial PRIMARY KEY,
  user_token character varying(150),
  user_rol character varying(150),
  username character varying(50) UNIQUE NOT NULL,
  password character varying(255) NOT NULL,
  img_url character varying(255),
  firstname character varying(50),
  lastname character varying(50),
  email character varying(100),
  birthdate timestamp,
  phone character varying(50),
  locality character varying(75),
  cod_verification int,
  valid_mail boolean DEFAULT false,
  created_at timestamp DEFAULT current_timestamp,
  active boolean DEFAULT true
);

CREATE TABLE roles (
  id serial PRIMARY KEY,
  rol_token character varying(150),
  title character varying(75),
  created_at timestamp DEFAULT current_timestamp
);

CREATE TABLE hospedajes (
  id serial PRIMARY KEY,
  nombre character varying(75),
  hospedaje_token character varying(150),
  user_token character varying(150),
  capacity int,
  description text,
  price numeric(10, 2),
  locality character varying(150),
  wifi boolean DEFAULT false,
  tv boolean DEFAULT false,
  garage boolean DEFAULT false,
  air_conditioning boolean DEFAULT false,
  heating boolean DEFAULT false,
  pool boolean DEFAULT false,
  created_at timestamp DEFAULT current_timestamp,
  active boolean DEFAULT true
);

CREATE TABLE ticket (
  id serial PRIMARY KEY,
  ticket_token character varying(150),
  user_token_owner character varying(150),
  user_token_renter character varying(150),
  hospedaje_token character varying(150),
  start_date date,
  end_date date,
  total_amount numeric(10, 2),
  created_at timestamp DEFAULT current_timestamp,
  active boolean DEFAULT true
);

CREATE TABLE hospedajes_img (
  id serial PRIMARY KEY,
  img_token character varying(150),
  hospedaje_token character varying(150),
  img_url character varying(255),
  created_at timestamp DEFAULT current_timestamp,
  active boolean DEFAULT true
);

-- Claves Foráneas
ALTER TABLE ticket 
  ADD FOREIGN KEY (hospedaje_token) REFERENCES hospedajes (hospedaje_token),
  ADD FOREIGN KEY (user_token_owner) REFERENCES users (user_token),
  ADD FOREIGN KEY (user_token_renter) REFERENCES users (user_token);

ALTER TABLE hospedajes_img 
  ADD FOREIGN KEY (hospedaje_token) REFERENCES hospedajes (hospedaje_token);

ALTER TABLE users 
  ADD FOREIGN KEY (user_rol) REFERENCES roles (rol_token);

ALTER TABLE hospedajes 
  ADD FOREIGN KEY (user_token) REFERENCES users (user_token);
