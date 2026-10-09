-- SMART CRM MEKONG MOBILE
-- Track SE - Luong L2: Tiep nhan va phan loai bao hanh
-- He quan tri CSDL: MySQL 8.0

CREATE DATABASE IF NOT EXISTS mekong_mobile
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE mekong_mobile;

-- 1. Khach hang
CREATE TABLE customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_customer_phone UNIQUE (phone),
    CONSTRAINT chk_customer_phone
        CHECK (phone REGEXP '^[0-9]{10,11}$')
);

-- 2. Thiet bi
CREATE TABLE device (
    device_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    device_name VARCHAR(100) NOT NULL,
    device_type VARCHAR(50) NOT NULL,
    serial_number VARCHAR(100) NOT NULL,

    CONSTRAINT fk_device_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id),

    INDEX idx_device_customer (customer_id)
);

-- 3. Nhom su co
CREATE TABLE issue_category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT uq_issue_category_name UNIQUE (name)
);

-- 4. Phieu bao hanh
CREATE TABLE ticket (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_code VARCHAR(30) NOT NULL,
    device_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    description VARCHAR(1000) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'MOI_TIEP_NHAN',
    received_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_ticket_code UNIQUE (ticket_code),

    CONSTRAINT fk_ticket_device
        FOREIGN KEY (device_id)
        REFERENCES device(device_id),

    CONSTRAINT fk_ticket_category
        FOREIGN KEY (category_id)
        REFERENCES issue_category(category_id),

    CONSTRAINT chk_ticket_priority
        CHECK (priority IN ('THAP', 'TRUNG_BINH', 'CAO')),

    INDEX idx_ticket_device (device_id),
    INDEX idx_ticket_category (category_id),
    INDEX idx_ticket_status_received (status, received_at)
);

-- 5. Lich su trang thai phieu
CREATE TABLE ticket_status_log (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    from_status VARCHAR(30),
    to_status VARCHAR(30) NOT NULL,
    changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_status_log_ticket
        FOREIGN KEY (ticket_id)
        REFERENCES ticket(ticket_id),

    INDEX idx_status_log_ticket_changed (ticket_id, changed_at)
);