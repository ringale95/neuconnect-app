const makeRequest = (url, method, data, onSuccess, onError) => {

	// Step 1 - Create a new XMLHttpRequest object
	const xhr = new XMLHttpRequest();

	// Step 2 - Construct the HTTP request
	xhr.open(method, url, true);

	// Set the Content-Type header if data is provided
	if (data) {
	  xhr.setRequestHeader('Content-Type', 'application/json');
	}

	// Step 3 - Set up event listeners for success and error
	xhr.onload = function () {
	  if (xhr.status >= 200 && xhr.status < 300) {
		// Successful response
        const contentType = xhr.getResponseHeader('content-type');
        let responseData;
		if (contentType && contentType.includes('application/json'))
          responseData = JSON.parse(xhr.responseText);
         else
          responseData = xhr.responseText;
		onSuccess(responseData);
	  } else {
		// Error response
		onError(xhr.statusText);
	  }
	};

	// Network error handling
	xhr.onerror = function () {
	  onError('Network error');
	};

	// Step 4 - Convert data object to JSON if provided and send the request
	const requestData = data ? JSON.stringify(data) : null;
	xhr.send(requestData);
  };

  export { makeRequest };
