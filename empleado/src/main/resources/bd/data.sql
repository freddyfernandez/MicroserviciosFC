INSERT INTO nacionalidad (nacionalidad_id, nacionalidad_nombre) VALUES (1,'Peru') ON CONFLICT (nacionalidad_id) DO NOTHING;
INSERT INTO nacionalidad (nacionalidad_id, nacionalidad_nombre) VALUES (2,'Argentina') ON CONFLICT (nacionalidad_id) DO NOTHING;
INSERT INTO nacionalidad (nacionalidad_id, nacionalidad_nombre) VALUES (3,'Brasil') ON CONFLICT (nacionalidad_id) DO NOTHING;