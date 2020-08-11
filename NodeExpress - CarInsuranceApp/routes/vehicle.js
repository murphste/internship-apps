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
const SQL_SELECT_ALL = 'SELECT * FROM dbo.Vehicle ORDER BY Model ASC for json path;';

// for json path, without_array_wrapper - use for single json result
const SQL_SELECT_BY_ID = 'SELECT * FROM dbo.Vehicle WHERE RegNo = @id for json path, without_array_wrapper;';

/*
// for json path, without_array_wrapper - use for single json result
const SQL_SELECT_BY_CATID = 'SELECT * FROM dbo.Product WHERE CategoryId = @id ORDER BY ProductName ASC for json path;';
*/


// Second statement (Select...) returns inserted record identified by DriverID = SCOPE_IDENTITY()
const SQL_INSERT = 'INSERT INTO dbo.Vehicle (RegNo, PersonDriverID, Model, Year, VehicleType) VALUES (@regNo, @personDriverID, @model, @year, @vehicleType); SELECT * from dbo.Vehicle WHERE RegNo = SCOPE_IDENTITY();';
const SQL_UPDATE = 'UPDATE dbo.Vehicle SET RegNo = @regNo, PersonDriverID = @PersonDriverID, Model = @model, Year = @year, VehicleType = @vehicleType  WHERE RegNo = @regNo; SELECT * FROM dbo.Vehicle WHERE RegNo = @regNo;';
const SQL_DELETE = 'DELETE FROM dbo.Vehicle WHERE RegNo = @regNo;';


// GET listing of all vehicles
// Address http://server:port/vehicle
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
// Address http://server:port/vehicle/:id
// returns JSON
router.get('/:id', async (req, res) => {

    // read value of id parameter from the request url
    const regNo = req.params.id;

    // Validate input - important as a bad input could crash the server or lead to an attack
    // See link to validator npm package (at top) for doc.
    // If validation fails return an error message
    // if (!validator.isstring(regNo, { no_symbols: false })) {
    //     res.json({ "error": "invalid reg no parameter" });
    //     return false;
    // }

    // If validation passed execute query and return results
    // returns a single vehicle by matching reg number
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            // set name parameter(s) in query
            .input('id', sql.string, regNo)
            // execute query
            .query(SQL_SELECT_BY_ID);

        // Send response with JSON result    
        res.json(result.recordset[0])

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
});



// POST - Insert a new vehicle.
// This async function sends a HTTP post request


// ---- The two lines below are the function header when using jwt authentication. The line beneath (106) allows HTTP POST via Postman when Passport jwt is disabled

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
    const regNo = validator.escape(req.body.RegNo);
    if (regNo === "") {
        errors+= "invalid reg No; ";
    }
    // Some custom validations - Make sure we don't have blank entries. Required fields ::
    const model = validator.escape(req.body.model);
    if (model === "") {
        errors+= "invalid model; ";
    }
    const vehicleType = validator.escape(req.body.VehicleType);
    if (vehicleType === "") {
        errors+= "invalid vehicle type; ";
    }
    // const email = validator.escape(req.body.email);
    // if (email === "") {
    //     errors+= "invalid email; ";
    // }

    // Make sure that email field is a String
    // if (!(typeof email === 'string' || email instanceof String)){
    //     errors+= "invalid email type entered; ";
    // }
    //Make sure the email address format is valid
    // if (!assertString(email)){
    //     errors+= "invalid email format; ";
    // }


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
            .input('regNo', sql.NVarChar, RegNo)    
            .input('personDriverID', sql.Int, PersonDriverID)
            .input('model', sql.NVarChar, Model)
            .input('year', sql.Int,  Year)
            .input('vehicleType', sql.NVarChar, VehicleType)
            // Execute Query
            .query(SQL_INSERT);      
    
        // If successful, return inserted person via HTTP   
        res.json(result.recordset[0]);

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
    
});

// PUT update vehicle
// Like post but driverID is provided and method = put

// ---- The two lines below are the function header when using jwt authentication. The line beneath (187) allows HTTP PUT via Postman when Passport jwt is disabled

router.put('/:id', passport.authenticate('jwt', { session: false}),
async (req, res) => {

    //router.put('/:id', async (req,res) => {

    // Validate input values (sent in req.body)
    let errors = "";
    const regNo = req.params.RegNo;

    if (!validator.isString(regNo, {no_symbols: false})) {
        errors+= "invalid reg no; ";
    }
    // Escape text and potentially bad characters
    regNo = validator.escape(req.body.regNo);
    if (regNo === "") {
        errors+= "invalid reg No; ";
    }
    // Some custom validations - Make sure we don't have blank entries. Required fields ::
    const model = validator.escape(req.body.model);
    if (lastName === "") {
        errors+= "invalid model; ";
    }
    const phone = validator.escape(req.body.VehicleType);
    if (phone === "") {
        errors+= "invalid vehicle type; ";
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
            .input('regNo', sql.NVarChar, RegNo)    
            .input('personDriverID', sql.Int, PersonDriverID)
            .input('model', sql.NVarChar, Model)
            .input('year', sql.Int,  Year)
            .input('vehicleType', sql.NVarChar, VehicleType)
            // Execute Query
            // run query
            .query(SQL_UPDATE);      
    
        // If successful, return updated person via HTTP    
        res.json(result.recordset[0]);

        } catch (err) {
        res.status(500)
        res.send(err.message)
        }
   
});

// DELETE single vehicle.

// ---- The two lines below are the function header when using jwt authentication. The line beneath (250) allows HTTP DELETE via Postman when Passport jwt is disabled

router.delete('/:id', passport.authenticate('jwt', { session: false}),
async (req, res) => {

    //router.delete('/:id', async (req,res) => {

    // Validate
    const regNo = req.params.RegNo;

    // If validation fails return an error message
    if (!validator.isString(regNo, { no_symbols: false })) {
        res.json({ "error": "invalid reg no" });
        return false;
    }
    
    // If no errors try delete
    try {
        // Get a DB connection and execute SQL
        const pool = await dbConnPoolPromise
        const result = await pool.request()
            .input('id', sql.String, regNo)
            .query(SQL_DELETE);      
    

        const rowsAffected = Number(result.rowsAffected);

        let response = {"deletedId": null}

        if (rowsAffected > 0)
        {
            response = {"deletedId": regNo}
        }

        res.json(response);

        } catch (err) {
            res.status(500)
            res.send(err.message)
        }
});

module.exports = router;
