// JavaScript for the Accidents page
//

// CRUD operations 


// Parse JSON
// Create rows for Accident list
// Display in web page
function displayAccidents(accidents) {

    // Use the Array map method to iterate through the array of people (drivers) (in json format)
    // Each products will be formated as HTML table rows and added to the array
    // see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map
    // Finally the output array is inserted as the content into the <tbody id="accidentRows"> element.
  
    const rows = accidents.map(accident => {
      // returns a template string for each product, values are inserted using ${ }
      // <tr> is a table row and <td> a table division represents a column
  
        let row = `<tr>
                <td>${accident.ReportNum}</td>
                <td>${accident.Location}</td>
                <td>${accident.IncidentDate}</td>

                
                `
        // <td class="price">&euro;${Number(product.ProductPrice).toFixed(2)}</td>
        // If user logged in then show edit and delete buttons
        // To add - check user role        
        if (userLoggedIn() === true) {      
            row+= `<td><button class="btn btn-xs" data-toggle="modal" data-target="#ProductFormDialog" onclick="prepareAccidentUpdate(${accident.ReportNum})"><span class="oi oi-pencil"></span></button></td>
                   <td><button class="btn btn-xs" onclick="deleteAccident(${accident.ReportNum})"><span class="oi oi-trash"></span></button></td>`
        }
        row+= '</tr>';

       return row;       
    });
  
    // Set the innerHTML of the productRows root element = rows
    // // join('') concatentates the array element into a string
    document.getElementById('accidentRows').innerHTML = rows.join('');
  } // end function
  

  
  // Get all accidents from db
  async function loadAccidentList() {
    try {
      
  
      const accidents = await getDataAsync(`${BASE_URL}accident`);
      displayAccidents(accidents);
  
    } // catch and log any errors
    catch (err) {
      console.log(err);
    }
  }
  

  
  // When an accident record is selected for update/ editing, get it by id (report num) and fill out the form
  async function prepareAccidentUpdate(id) {

    try {
        // Get person by id
        const accident = await getDataAsync(`${BASE_URL}accident/${ReportNum}`);
        // Fill out the form
        document.getElementById('reportNum').value = accident.reportNum; // uses a hidden field - see the form
        document.getElementById('location').value = accident.Location;
        document.getElementById('incidnetDate').value = accident.IncidentDate;

    } // catch and log any errors
    catch (err) {
    console.log(err);
    }
  }


  // Called when form submit button is clicked
  async function addOrUpdateAccident() {
  
    // url
    let url = `${BASE_URL}accident`
  
    // Get form fields
    const id = document.getElementById('reportNum').value; // uses a hidden field - see the form
    const location = document.getElementById('location').value;
    const incidentDate = document.getElementById('incidentDate').value;

    // build request body
    const reqBody = JSON.stringify({
    reportNum: reportNum,
    location: location,
    incidentDate: incidentDate
    });

    // Try catch 
    try {
        let json = "";
        // determine if this is an insert (POST) or update (PUT)
        // update will include reportNum (id)
        if (id > 0) {
            url+= `/${id}`;
            json = await postOrPutDataAsync(url, reqBody, 'PUT');
        }
        else {  
            json = await postOrPutDataAsync(url, reqBody, 'POST');
        }
      // Load persons
      loadAccidentList();
      // catch and log any errors
    } catch (err) {
      console.log(err);
      return err;
    }
  }

  // Delete accident by id (report num) using an HTTP DELETE request
  async function deleteAccident(id) {
        
    if (confirm("Are you sure?")) {
        // url
        const url = `${BASE_URL}accident/${id}`;
        
        // Try catch 
        try {
            const json = await deleteDataAsync(url);
            console.log("response: " + json);

            loadAccidentList();

        // catch and log any errors
        } catch (err) {
            console.log(err);
            return err;
        }
    }
  }

 // Show accident button
 function showAddAccidentButton() {

  let addAccidentButton= document.getElementById('AddAccidentButton');

  if (userLoggedIn() === true) {
    addAccidentButton.style.display = 'block';
  }
  else {
    addAccidentButton.style.display = 'none';
  }
 }

// show login or logout
showLoginLink();

// Load accidents
loadAccidentList();

showAddAccidentButton();