DROP TABLE price IF EXISTS;

CREATE TABLE IF NOT EXISTS price (
id          LONG            PRIMARY KEY,
product_id  VARCHAR         NOT NULL,
brand_id    LONG            NOT NULL,
start_at    TIMESTAMP       NOT NULL,
end_at      TIMESTAMP       NOT NULL,
price_list  VARCHAR         NOT NULL,
priority    INT             NOT NULL,
amount      DECIMAL(30,2)   NOT NULL,
currency    VARCHAR(3)      NOT NULL
);

INSERT INTO price (id, product_id, brand_id, start_at, end_at, price_list, priority, amount, currency) values
(1, '35455', 1, '2020-06-14T00.00.00Z', '2020-12-31T23.59.59Z', 1, 0, 35.50, 'EUR'),
(2, '35455', 1, '2020-06-14T15.00.00Z', '2020-06-14T18.30.00Z', 2, 1, 25.45, 'EUR'),
(3, '35455', 1, '2020-06-15T00.00.00Z', '2020-06-15T11.00.00Z', 3, 1, 30.50, 'EUR'),
(4, '35455', 1, '2020-06-15T16.00.00Z', '2020-12-31T23.59.59Z', 4, 1, 38.95, 'EUR');
