// JavaScript for the Vehicle page
//

// CRUD operations 


// Parse JSON
// Create rows for Vehicle list
// Display in web page
function displayVehicles(vehicles) {

    // Use the Array map method to iterate through the array of people (drivers) (in json format)
    // Each vehicle will be formated as HTML table rows and added to the array
    // see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map
    // Finally the output array is inserted as the content into the <tbody id="vehicleRows"> element.
  
    const rows = vehicles.map(vehicle => {
      // returns a template string for each vehicle, values are inserted using ${ }
      // <tr> is a table row and <td> a table division represents a column
  
        let row = `<tr>
                <td>${vehicle.RegNo}</td>
                <td>${vehicle.PersonDriverID}</td>
                <td>${vehicle.Model}</td>
                <td>${vehicle.Year}</td>
                <td>${vehicle.VehicleType}</td>`
      
        // If user logged in then show edit and delete buttons
        // To add - check user role        
        if (userLoggedIn() === true) {      
            row+= `<td><button class="btn btn-xs" data-toggle="modal" data-target="#VehicleFormDialog" onclick="prepareVehicleUpdate(${vehicle.RegNo})"><span class="oi oi-pencil"></span></button></td>
                   <td><button class="btn btn-xs" onclick="deleteVehicle(${vehicle.RegNo})"><span class="oi oi-trash"></span></button></td>`
        }
        row+= '</tr>';

       return row;       
    });
  
    // Set the innerHTML of the vehicleRows root element = rows
    // join('') concatentates the array element into a string
    document.getElementById('vehicleRows').innerHTML = rows.join('');
  } // end function
  
  
  
  
  // Get all vehciles then display
  async function loadVehicleList() {
    try {
      
    //   const categories = await getDataAsync(`${BASE_URL}category`);
    //   displayCategories(categories);
  
      const vehicles = await getDataAsync(`${BASE_URL}vehicle`);
      displayVehicles(vehicles);
  
    } // catch and log any errors
    catch (err) {
      console.log(err);
    }
  }
  
  
  // When a vehicle is selected for update/ editing, get it by id and fill out the form
  async function prepareVehicleUpdate(id) {

    try {
        // Get vehicle by id
        const vehicle = await getDataAsync(`${BASE_URL}vehicle/${id}`);
        // Fill out the form
        document.getElementById('regNo').value = vehicles.RegNo;
        document.getElementById('personDriverID').value = vehicle.PersonDriverID;
        document.getElementById('Model').value = vehicle.Model;
        document.getElementById('Year').value = vehicle.Year;
        document.getElementById('Vehicle Type').value = vehicle.VehicleType;
    } // catch and log any errors
    catch (err) {
    console.log(err);
    }
  }

  // Called when form submit button is clicked
  async function addOrUpdateVehicle() {
  
    // url
    let url = `${BASE_URL}vehicle`
  
    // Get form fields
    const regNo =  document.getElementById('regNo').value;
    const personDriverID = document.getElementById('personDriverID').value;
    const model = document.getElementById('model').value;
    const year = Number(document.getElementById('year').value);
    const vehicleType = document.getElementById('vehicleType').value;

    // build request body
    const reqBody = JSON.stringify({
    regNo: regNo,
    personDriverID: personDriverID,
    model: model,
    year: year,
    vehicleType: vehicleType
    });

    // Try catch 
    try {
        let json = "";
        // determine if this is an insert (POST) or update (PUT)
        // update will include vehicle id (reg no)
        if (id > 0) {
            url+= `/${id}`;
            json = await postOrPutDataAsync(url, reqBody, 'PUT');
        }
        else {  
            json = await postOrPutDataAsync(url, reqBody, 'POST');
        }
      // Load vehicles
      loadVehicleList();
      // catch and log any errors
    } catch (err) {
      console.log(err);
      return err;
    }
  }

  // Delete vehicle by id using an HTTP DELETE request
  async function deleteVehicle(id) {
        
    if (confirm("Are you sure?")) {
        // url
        const url = `${BASE_URL}vehicle/${id}`;
        
        // Try catch 
        try {
            const json = await deleteDataAsync(url);
            console.log("response: " + json);

            loadVehicleList();

        // catch and log any errors
        } catch (err) {
            console.log(err);
            return err;
        }
    }
  }

 // Show vehicle button
 function showAddVehicleButton() {

  let addVehicleButton= document.getElementById('AddVehicleButton');

  if (userLoggedIn() === true) {
    addVehicleButton.style.display = 'block';
  }
  else {
    addVehicleButton.style.display = 'none';
  }
 }

// show login or logout
showLoginLink();

// Load vehicles
loadVehicleList();

showAddVehicleButton();