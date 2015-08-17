/*
  Предустановки системы
*/

-- Создание пользователя admin с ролью ROLE_ADMIN, если таких нет
-- password = 1, see org.demo.utils.Utils.GenerateHashedPassword
INSERT INTO users (id, username, password)
SELECT 1, 'admin', '$2a$10$ibRP08qCKADwdjjwymscI.XFoA.QFoveqdRbsh7VIiXGQff/ZMRZW'
WHERE NOT EXISTS(SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO roles (id, role)
SELECT 1, 'ROLE_ADMIN'
WHERE NOT EXISTS(SELECT 1 FROM roles WHERE role = 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
SELECT 1, 1
WHERE EXISTS(SELECT 1 FROM users WHERE username = 'admin')
  AND EXISTS(SELECT 1 FROM roles WHERE role = 'ROLE_ADMIN')
  AND NOT EXISTS(SELECT 1 FROM users_roles WHERE user_id = 1 AND role_id = 1);
