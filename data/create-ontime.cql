
/**
** drop data space if it exists in current data space
**/
DROP KEYSPACE IF EXISTS isd_time_data;
CREATE KEYSPACE isd_time_data WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use isd_time_data;

/**
** create data table for raw data //wsid text,
**/
CREATE TABLE raw_time_data (

     month Int,
     dayofMonth Int,
     dayOfWeek Int,
     depTime Int,
     csrDepTime Int,
     arrTime Int,
     uniqueCarrier text,
     flightNum text,
     tailNum text,
     actualElapsedTime Int,
     crsElapsedTime Int,
     airTime Int,
     arrDelay Int,
     depDelay Int,
     origin text,
     dest  text,
     Distance  Int, PRIMARY KEY (month, dayofMonth, dayOfWeek, depTime))

