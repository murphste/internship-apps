const router = require('express').Router();

const jwt = require('jsonwebtoken');
const passport = require('passport');

// Input validation package
// https://www.npmjs.com/package/validator
const validator = require('validator');

// require the database connection
const { sql, dbConnPoolPromise } = require('../database/db.js');

// Define SQL statements here for use in function below
// These are parameterised queries note @named parameters.
// Input parameters are parsed and set before queries are executed

// for json path - Tell MS SQL to return results as JSON 
const SQL_SELECT_ALL = 'SELECT * FROM dbo.Person ORDER BY LastName ASC for json path;';

// for json path, without_array_wrapper - use for single json result
const SQL_SELECT_BY_ID = 'SELECT * FROM dbo.Person WHERE DriverID = @id for json path, without_array_wrapper;';

/*
// for json path, without_array_wrapper - use for single json result
const SQL_SELECT_BY_CATID = 'SELECT * FROM dbo.Product WHERE CategoryId = @id ORDER BY ProductName ASC for json path;';
*/


// Second statement (Select...) returns inserted record identified by DriverID = SCOPE_IDENTITY()
const SQL_INSERT = 'INSERT INTO dbo.Person (DriverID, FirstName, LastName, Phone, Email, StreetAddress, City, Country, PenaltyPoints) VALUES (@driverId, @firstName, @lastName, @phone, @email, @streetAddress, @city, @country, @penaltyPoints); SELECT * from dbo.Person WHERE DriverID = SCOPE_IDENTITY();';
const SQL_UPDATE = 'UPDATE dbo.Person SET FirstName = @firstName, LastName = @lastName, Phone = @phone, Email = @email, StreetAddress = @streetAddress, City = @city, Country = @country, PenaltyPoints = @penaltyPoints  WHERE DriverID = @id; SELECT * FROM dbo.Person WHERE DriverID = @id;';
const SQL_DELETE = 'DELETE FROM dbo.Person WHERE DriverID = @id;';


// GET listing of all persons (drivers)
// Address http://server:port/person
// returns JSON
router.get('/', async (req, res) => {

    // Get a DB connection and execute SQL
    try {
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            // execute query
            .query(SQL_SELECT_ALL);
        
        // Send HTTP response.
        // JSON data from MS SQL is contained in first element of the recordset.
        res.json(result.recordset[0]);

      // Catch and send errors  
      } catch (err) {
        res.status(500)
        res.send(err.message)
      }
});

// GET a single person by id from driver list
// id passed as parameter via url
// Address http://server:port/person/:id
// returns JSON
router.get('/:id', async (req, res) => {

    // read value of id parameter from the request url
    const driverID = req.params.id;

    // Validate input - important as a bad input could crash the server or lead to an attack
    // See link to validator npm package (at top) for doc.
    // If validation fails return an error message
    if (!validator.isNumeric(driverID, { no_symbols: true })) {
        res.json({ "error": "invalid id parameter" });
        return false;
    }

    // If validation passed execute query and return results
    // returns a single person with matching DriverID
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            // set name parameter(s) in query
            .input('id', sql.Int, driverID)
            // execute query
            .query(SQL_SELECT_BY_ID);

        // Send response with JSON result    
        res.json(result.recordset[0])

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
});


// POST - Insert a new person.
// This async function sends a HTTP post request

// ---- The two lines below are the function header when using jwt authentication. The line beneath (104) allows HTTP POST via Postman when Passport jwt is disabled

router.post('/', passport.authenticate('jwt', { session: false}),
async (req, res) => {

//router.post('/', async (req,res) => {

    // Validate - this string, inially empty, will store any errors
    let errors = "";
    /*
    // Make sure that category id is just a number - note that values are read from request body
    const categoryId = req.body.categoryId;
    if (!validator.isNumeric(categoryId, {no_symbols: true})) {
        errors+= "invalid category id; ";
    }
    */
    
    // Escape text and potentially bad characters
    const firstName = validator.escape(req.body.firstName);
    if (firstName === "") {
        errors+= "invalid firstName; ";
    }
    // Some custom validations - Make sure we don't have blank entries. Required fields ::
    const lastName = validator.escape(req.body.lastName);
    if (lastName === "") {
        errors+= "invalid lastName; ";
    }
    const phone = validator.escape(req.body.phone);
    if (phone === "") {
        errors+= "invalid phone; ";
    }
    const email = validator.escape(req.body.email);
    if (email === "") {
        errors+= "invalid email; ";
    }

    // Make sure that email field is a String
    if (!(typeof email === 'string' || email instanceof String)){
        errors+= "invalid email type entered; ";
    }


    // If errors send details in response
    if (errors != "") {
        // return http response with  errors if validation failed
        res.json({ "error": errors });
        return false;
    }

    // If no errors, insert
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            // set named parameter(s) in query
            .input('driverId', sql.Int, driverId)
            .input('firstName', sql.NVarChar, firstName)    
            .input('lastName', sql.NVarChar, lastName)
            .input('phone', sql.NVarChar, phone)
            .input('email', sql.NVarChar,  email)
            .input('streetAddress', sql.NVarChar, streetAddress)
            .input('city', sql.NVarChar, City)
            .input('country', sql.NVarChar,  Country)
            .input('penaltyPoints', sql.Int, penaltyPoints)
            // Execute Query
            .query(SQL_INSERT);      
    
        // If successful, return inserted person via HTTP   
        res.json(result.recordset[0]);

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
    
});

// PUT update person
// Like post but driverID is provided and method = put

// ---- The two lines below are the function header when using jwt authentication. The line beneath (104) allows HTTP PUT via Postman when Passport jwt is disabled

router.put('/:id', passport.authenticate('jwt', { session: false}),
async (req, res) => {

//router.put('/:id', async (req,res) => {

    // Validate input values (sent in req.body)
    let errors = "";
    const driverId = req.params.DriverID;

    if (!validator.isNumeric(driverID, {no_symbols: true})) {
        errors+= "invalid Driver id; ";
    }
    // Escape text and potentially bad characters
    const firstName = validator.escape(req.body.firstName);
    if (firstName === "") {
        errors+= "invalid firstName; ";
    }
    // Some custom validations - Make sure we don't have blank entries. Required fields ::
    const lastName = validator.escape(req.body.lastName);
    if (lastName === "") {
        errors+= "invalid lastName; ";
    }
    const phone = validator.escape(req.body.phone);
    if (phone === "") {
        errors+= "invalid phone; ";
    }
    const email = validator.escape(req.body.email);
    if (email === "") {
        errors+= "invalid email; ";
    }

    // Make sure that email field is a String
    if (!(typeof email === 'string' || email instanceof String)){
        errors+= "invalid email type entered; ";
    }
    //Make sure the email address format is valid
    if (!email.includes("@")){
        errors+= "invalid email format; ";
    }

    // If errors send details in response
    if (errors != "") {
        // return http response with  errors if validation failed
        res.json({ "error": errors });
        return false;
    }

    // If no errors
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            // set parameters
            .input('firstName', sql.NVarChar, firstName)    
            .input('lastName', sql.NVarChar, lastName)
            .input('phone', sql.NVarChar, Phone)
            .input('email', sql.NVarChar,  email)
            .input('streetAddress', sql.NVarChar, streetAddress)
            .input('city', sql.NVarChar, City)
            .input('country', sql.NVarChar,  Country)
            .input('penaltyPoints', sql.Int, penaltyPoints)
            // run query
            .query(SQL_UPDATE);      
    
        // If successful, return updated person via HTTP    
        res.json(result.recordset[0]);

        } catch (err) {
        res.status(500)
        res.send(err.message)
        }
   
});

// DELETE single person.


// ---- The two lines below are the function header when using jwt authentication. The line beneath (264) allows HTTP DELETE via Postman when Passport jwt is disabled

router.delete('/:id', passport.authenticate('jwt', { session: false}),
async (req, res) => {


// router.delete('/:id', async (req,res) => {

    // Validate
    const driverID = req.params.DriverID;

    // If validation fails return an error message
    if (!validator.isNumeric(driverID, { no_symbols: true })) {
        res.json({ "error": "invalid id parameter" });
        return false;
    }
    
    // If no errors try delete
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            .input('id', sql.Int, driverID)
            .query(SQL_DELETE);      
    

        const rowsAffected = Number(result.rowsAffected);

        let response = {"deletedId": null}

        if (rowsAffected > 0)
        {
            response = {"deletedId": driverID}
        }

        res.json(response);

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
});

module.exports = router;
