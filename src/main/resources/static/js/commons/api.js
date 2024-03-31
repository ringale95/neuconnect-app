const makeRequest = (url, method, data, onSuccess, onError) => {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url, true);

    // Set the Content-Type header if data is provided
    if (data) {
        xhr.setRequestHeader('Content-Type', 'application/json');
    }

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            let responseData = null;
            const contentType = xhr.getResponseHeader('content-type');
            if (contentType && contentType.includes('application/json')) {
                responseData = JSON.parse(xhr.responseText);
            } else {
                responseData = xhr.responseText;
            }
            onSuccess(responseData);
        } else {
            onError(xhr.statusText);
        }
    };

    xhr.onerror = function () {
        onError('Network error');
    };

    const requestData = data ? JSON.stringify(data) : null;
    xhr.send(requestData);
};

export { makeRequest };
