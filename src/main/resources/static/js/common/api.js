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

  const makeRequestFormData = (url, method, formData, onSuccess, onError) => {

      // Step 1 - Create a new XMLHttpRequest object
      const xhr = new XMLHttpRequest();

      // Step 2 - Construct the HTTP request
      xhr.open(method, url, true);

      // Set up event listeners for success and error
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

      // Set up the request with credentials
      xhr.withCredentials = true;

      // Step 3 - Create a FormData object and append the form data
      const data = new FormData();
      data.append("file", formData.file);
      data.append("type", formData.type);

      // Step 4 - Send the request
      xhr.send(data);
  };


  export { makeRequest, makeRequestFormData };
