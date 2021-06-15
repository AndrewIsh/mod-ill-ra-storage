\c database_name

CREATE SCHEMA schema_name AUTHORIZATION user_name;

CREATE TABLE schema_name.ill_request (_id SERIAL PRIMARY KEY, jsonb JSONB NOT NULL);
GRANT ALL ON schema_name.ill_request TO user_name;
GRANT ALL ON schema_name.ill_request__id_seq TO user_name;
