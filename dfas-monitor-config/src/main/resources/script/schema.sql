CREATE TABLE  IF NOT EXISTS T_MICRO_SERVICE (
  ID NUMBER PRIMARY KEY,
  MICROSERVICENAME VARCHAR,
  MICROSERVICEALIAS VARCHAR,
  INSTANCEID VARCHAR,
  HOSTNAME VARCHAR,
  APP VARCHAR,
  IPADDR VARCHAR
);
CREATE TABLE  IF NOT EXISTS DC_DEVCIE (
  ID VARCHAR PRIMARY KEY,
  NAME VARCHAR,
  ALIAS VARCHAR,
  IP_ADDRESS VARCHAR,
  USER_NAME VARCHAR,
  PORT INT,
  OS_TYPE VARCHAR,
  STATUS INT,
  DESCRIPTION VARCHAR,
  CREATE_TIME VARCHAR,
  UPDATE_TIME VARCHAR,
  CPU VARCHAR,
  MEMORY VARCHAR,
  DISK VARCHAR,
  CPUNUM INT,
  DISKSIZE VARCHAR,
  MEMORYSIZE VARCHAR,
  BALANCE VARCHAR
);
CREATE TABLE  IF NOT EXISTS T_ISSUE (
  ID VARCHAR PRIMARY KEY,
  IP_ADDRESS VARCHAR,
  ISSUE_DESC VARCHAR,
  WARN_LEVEL VARCHAR,
  CREATE_TIME VARCHAR,
  UPDATE_TIME VARCHAR
);
CREATE TABLE  IF NOT EXISTS T_THRESHOLD (
  ID VARCHAR PRIMARY KEY,
  INDICATOR_BODY VARCHAR,
  INDICATOR_NAME VARCHAR,
  INDICATOR_SYMBOL VARCHAR,
  THRESHOLD INT,
  CREATE_TIME VARCHAR,
  UPDATE_TIME VARCHAR
);
