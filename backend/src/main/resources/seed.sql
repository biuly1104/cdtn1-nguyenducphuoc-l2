USE mekong_mobile;

-- Du lieu khach hang mau
INSERT INTO customer (full_name, phone)
VALUES
    ('Nguyen Van A', '0912345678'),
    ('Tran Thi B', '0987654321')
ON DUPLICATE KEY UPDATE
    full_name = VALUES(full_name);

-- Du lieu nhom su co mau
INSERT INTO issue_category (name)
VALUES
    ('Loi man hinh'),
    ('Loi pin'),
    ('Loi phan mem')
ON DUPLICATE KEY UPDATE
    name = VALUES(name);