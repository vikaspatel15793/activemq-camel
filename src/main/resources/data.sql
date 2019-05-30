DROP TABLE IF EXISTS transaction;
 
CREATE TABLE transaction (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  transId VARCHAR(250) DEFAULT NULL,
  effectiveEntryDateTime VARCHAR(250) DEFAULT NULL,
  processingDateTime VARCHAR(250) DEFAULT NULL,
  debitAccountNumber VARCHAR(250) DEFAULT NULL,
  creditAccountNumber VARCHAR(250) DEFAULT NULL,
  amount float DEFAULT NULL,
);
