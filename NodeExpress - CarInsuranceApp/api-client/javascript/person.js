// JavaScript for the Person page
//

// CRUD operations 


// Parse JSON
// Create rows for Person list
// Display in web page
function displayPersons(persons) {

    // Use the Array map method to iterate through the array of people (drivers) (in json format)
    // Each person will be formated as HTML table rows and added to the array
    // see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map
    // Finally the output array is inserted as the content into the <tbody id="personRows"> element.
  
    const rows = persons.map(person => {
      // returns a template string for each person, values are inserted using ${ }
      // <tr> is a table row and <td> a table division represents a column
  
        let row = `<tr>
                <td>${person.DriverID}</td>
                <td>${person.FirstName}</td>
                <td>${person.LastName}</td>
                <td>${person.Phone}</td>
                <td>${person.Email}</td>
                <td>${person.StreetAddress}</td>
                <td>${person.City}</td>
                <td>${person.Country}</td>
                <td>${person.PenaltyPoints}</td>`
      
        

        // If user logged in then show edit and delete buttons
        // To add - check user role        
        if (userLoggedIn() === true) {      
            row+= `<td><button class="btn btn-xs" data-toggle="modal" data-target="#PersonFormDialog" onclick="preparePersonUpdate(${person.DriverID})"><span class="oi oi-pencil"></span></button></td>
                   <td><button class="btn btn-xs" onclick="deletePerson(${person.DriverID})"><span class="oi oi-trash"></span></button></td>`
        }
        row+= '</tr>';

       return row;       
    });
  
    // Set the innerHTML of the personRows root element = rows
    // // join('') concatentates the array element into a string
    document.getElementById('personRows').innerHTML = rows.join('');
  } // end function
  

  
  
  
  // Get all persons and display
  async function loadPersonList() {
    try {
      
      const persons = await getDataAsync(`${BASE_URL}person`);
      displayPersons(persons);
  
    } // catch and log any errors
    catch (err) {
      console.log(err);
    }
  }
  
  
  
  // When a person record is selected for update/ editing, get it by id and fill out the form
  async function preparePersonUpdate(id) {

    try {
        // Get person by id
        const person = await getDataAsync(`${BASE_URL}person/${DriverID}`);
        // Fill out the form
        document.getElementById('driverID').value = person.DriverID; // uses a hidden field - see the form
        document.getElementById('firstName').value = person.FirstName;
        document.getElementById('lastName').value = person.LastName;
        document.getElementById('phone').value = person.Phone;
        document.getElementById('email').value = person.Email;
        document.getElementById('streetAddress').value = person.StreetAddress;
        document.getElementById('city').value = person.City;
        document.getElementById('country').value = person.Country;
        document.getElementById('penaltyPoints').value = person.PenaltyPoints;
    } // catch and log any errors
    catch (err) {
    console.log(err);
    }
  }

  // Called when form submit button is clicked
  async function addOrUpdatePerson() {
  
    // url
    let url = `${PERSON_URL}person`
  
    // Get form fields
    const id = Number(document.getElementById('driverID').value);
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const streetAddress = document.getElementById('streetAddress').value;
    const city = document.getElementById('city').value;
    const country = document.getElementById('country').value;
    const penaltyPoints = document.getElementById('penaltyPoints').value;

    // build request body
    const reqBody = JSON.stringify({
    firstName: firstName,
    lastName: lastName,
    phone: phone,
    email: email,
    streetAddress: streetAddress,
    city: city,
    country: country,
    penaltyPoints: penaltyPoints
    });

    // Try catch 
    try {
        let json = "";
        // determine if this is an insert (POST) or update (PUT)
        // update will include person id (driverID)
        if (id > 0) {
            url+= `/${id}`;
            json = await postOrPutDataAsync(url, reqBody, 'PUT');
        }
        else {  
            json = await postOrPutDataAsync(url, reqBody, 'POST');
        }
      // Load persons
      loadPersonList();
      // catch and log any errors
    } catch (err) {
      console.log(err);
      return err;
    }
  }

  // Delete person by id (driverID) using an HTTP DELETE request
  async function deletePerson(id) {
        
    if (confirm("Are you sure?")) {
        // url
        const url = `${PERSON_URL}person/${id}`;
        
        // Try catch 
        try {
            const json = await deleteDataAsync(url);
            console.log("response: " + json);

            loadPersonList();

        // catch and log any errors
        } catch (err) {
            console.log(err);
            return err;
        }
    }
  }

 // Show person button
 function showAddPersonButton() {

  let addPersonButton= document.getElementById('AddPersonButton');

  if (userLoggedIn() === true) {
    addPersonButton.style.display = 'block';
  }
  else {
    addPersonButton.style.display = 'none';
  }
 }

// show login or logout
showLoginLink();

// Load person list
loadPersonList();

showAddPersonButton();