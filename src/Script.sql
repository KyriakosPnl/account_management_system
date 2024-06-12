CREATE TABLE account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    beneficiary_id INT
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    amount DECIMAL(10, 2),
    type VARCHAR(50),
    date DATE,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE beneficiary (
    beneficiary_id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);

ALTER TABLE account
ADD CONSTRAINT fk_beneficiary_id
FOREIGN KEY (beneficiary_id) REFERENCES beneficiarY(beneficiary_id);

LOAD DATA INFILE '/var/lib/mysql-files/trial_project/beneficiaries.csv'
INTO TABLE beneficiary
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(beneficiary_id,first_name, last_name);

LOAD DATA INFILE '/var/lib/mysql-files/trial_project/accounts.csv'
INTO TABLE account
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(account_id, beneficiary_id);

LOAD DATA INFILE '/var/lib/mysql-files/trial_project/transactions.csv'
INTO TABLE transaction
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(transaction_id, account_id, amount, type, @date)
SET date = STR_TO_DATE(@date, '%m/%d/%y');

SELECT * FROM transaction;
SELECT * FROM account;
SELECT * FROM beneficiary;




