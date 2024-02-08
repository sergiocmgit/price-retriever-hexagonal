# Read Me First

The purpose of this project is to build a demo service
using **domain-driven design** and **hexagonal architecture**.
The topic of the exercise is about prices linked to
timeframes and the query of them.

For this practice, the next instructions are given:

- The service must be built with SpringBoot.
- The service must provide an endpoint to query prices.
- The endpoint must admit as input and output the following parameters:
    - input: date of application, identifier of the product, identifier of the brand.
    - output: identifier of the product, identifier of the brand, price list to be applied, timeframe of the
      application and final price to be applied.
- The database must be an H2 initialised with the rows described below. Column names can be changed and
  added if needed, and the column types can be chosen to the best fit.
- There have to be implemented tests on the endpoint validating the following inputs:
    - Test 1: request at 10:00 on the 14th for the product 35455 for the brand 1
    - Test 2: request at 16:00 on the 14th for the product 35455 for the brand 1
    - Test 3: request at 21:00 on the 14th for the product 35455 for the brand 1
    - Test 4: request at 10:00 on the 15th for the product 35455 for the brand 1
    - Test 5: request at 21:00 on the 16th for the product 35455 for the brand 1

----

#### PRICES
| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  | 
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  | 
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  | 
