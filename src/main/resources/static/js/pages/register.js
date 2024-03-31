import { makeRequest } from "../commons/api.js";

// Wait for page to load using DOMContentLoaded
if (window.location.pathname.includes('/neuconnect/register')){
    document.addEventListener('DOMContentLoaded', () => {
        const form =  document.getElementById("register-form");

        // Add event listener on form
        form.addEventListener('submit', onSubmit);
    });
}

const onSubmit = (event) => {
    event.preventDefault();

    // Fetch Form-data
    const formData = new FormData(event.target);

    // Construct user object
    const user = {
        fname: formData.get('fname'),
        lname: formData.get('lname'),
        nuid: formData.get('nuid'),
        dob: formData.get('dob'),
        gender: formData.get('gender'),
        username: formData.get('username'),
        password: formData.get('password')
    };

    // Invoke makeRequest(url, method, data, onSuccess, onError)
    makeRequest("/neuconnect/register", "POST", user, onSuccess, onError);
}

const onSuccess = (response) => {
    window.location.href = '/neuconnect';
}

const onError = (response) => {
    window.location.href = '/neuconnect/register?status=FAILED';
}

export { onSubmit };
